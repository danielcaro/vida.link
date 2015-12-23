/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.inject.Singleton;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import link.vida.conn.ConnService;

/**
 *
 * @author dcaro
 */
@Singleton
public class ConnZMQService extends Thread  implements ConnService{

    static final int PORT = Integer.parseInt(System.getProperty("port", "777"));
    private Integer connectorId;

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // http://netty.io/wiki/new-and-noteworthy-in-4.0.html#wiki-h3-31
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new VDLChInit());
            // Start the server.
            ChannelFuture f = b.bind(PORT).sync();
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
        } catch (InterruptedException ex) {
            Logger.getLogger(ConnZMQService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
                // Wait until all threads are terminated.
                bossGroup.terminationFuture().sync();
                workerGroup.terminationFuture().sync();
            } catch (InterruptedException ex) {
                Logger.getLogger(ConnZMQService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

    @Override
    public String getConnName() {
        return "CONN.ZMQ";
    }

    @Override
    public Integer getConnectorId() {
        return connectorId;
    }

    @Override
    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }


}
