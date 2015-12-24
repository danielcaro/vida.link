/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.inject.Inject;
import com.spotify.netty4.handler.codec.zmtp.ZMTPCodec;
import com.spotify.netty4.handler.codec.zmtp.ZMTPConfig;
import com.spotify.netty4.handler.codec.zmtp.ZMTPIdentityGenerator;
import com.spotify.netty4.handler.codec.zmtp.ZMTPSession;
import com.spotify.netty4.handler.codec.zmtp.ZMTPSocketType;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;
import link.vida.app.VidaLink;
import link.vida.db.vdl.VdlDao;
import link.vida.session.VDLPeersManager;
import link.vida.session.VDLPeersManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class ZMQChInit extends ChannelInitializer {

    public static final ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private static final Logger log = LoggerFactory.getLogger(ZMQChInit.class);

//    private static final PeersManager peersManager = new ZMQPeersManager();

    @Inject
    VDLPeersManager peersManager;
    
    @Inject
    VdlDao vdlDao;
    
    @Inject 
    ZMTPIdentityGenerator zMTPIdentityGenerator;
    
    
    /**
     * Al iniciar un canal sucede lo siguiente.
     *
     * @param ch
     * @throws Exception
     */
    @Override

    protected void initChannel(final Channel ch) throws Exception {
        channelGroup.add(ch);
        log.info("Nueva Conexión, total " + channelGroup.size());
        

        ZMTPCodec codec = ZMTPCodec.from(
                ZMTPConfig.builder().
                socketType(ZMTPSocketType.ROUTER).
                identityGenerator(zMTPIdentityGenerator).
                build());

        // mejorar la entrega de codec

//        ch.pipeline().addLast("readTimeoutHandler", new ReadTimeoutHandler(30));
        ch.pipeline().addLast("zmtp-codec", codec);
        ZMQPeerImpl peer = new ZMQPeerImpl(ch, codec.session(), peersManager);
        // Injectando los miembros de ese objeto
        VidaLink.injector.injectMembers(peer);
        ch.pipeline().addLast("peer-handler", peer);
        
       vdlDao.peersList();
    }
        
    

    public VDLPeersManager getPeersManager() {
        return peersManager;
    }


}
