/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vidaware.vida.link;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.spotify.netty4.handler.codec.zmtp.ZMTPHandshakeSuccess;
import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;
import com.spotify.netty4.handler.codec.zmtp.ZMTPSession;
import com.spotify.netty4.util.BatchFlusher;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.ReadTimeoutHandler;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.vidaware.vida.link.ListenableFutureAdapter.listenable;
import net.vidaware.vida.link.msgs.VDLMsg;

/**
 *
 * @author dcaro
 */
public class VDLPeer extends ChannelInboundHandlerAdapter implements Peer {

    private static final Logger log = LoggerFactory.getLogger(VDLPeer.class);

    private final Channel ch;
    private final ZMTPSession session;
    private final Handler handler;
    private final PeersManager peersManager;

    public VDLPeer(final Channel ch, final ZMTPSession session, PeersManager peersManager) {
        this.ch = ch;
        this.session = session;
        this.handler = new VLDPeersHandler();
        this.peersManager = peersManager;

        // Setear el tiemout segun el tipo de dispositivo que se conecta
        // ch.pipeline().addBefore("zmtp-codec", "set-sotime", new ReadTimeoutHandler(60));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx)
            throws Exception { //1
//        System.out.println(
//                "Client " + ctx.channel().remoteAddress() + " connected");
    }

    /**
     * Create a human readable string representation of binary data, keeping
     * printable ascii and hex encoding everything else.
     *
     * @param data The data
     * @return A human readable string representation of the data.
     */
    private static String toString(final ByteBuf data) {
        if (data == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = data.readerIndex(); i < data.writerIndex(); i++) {
            final byte b = data.getByte(i);
            if (b > 31 && b < 127) {
                if (b == '%') {
                    sb.append('%');
                }
                sb.append((char) b);
            } else {
                sb.append('%');
                sb.append(String.format("%02X", b));
            }
        }
        return sb.toString();
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg)
            throws Exception {
        if (msg instanceof ZMTPMessage) {
            try {
                ZMTPMessage mensaje = (ZMTPMessage) msg;

                String jsonMsg = toString(mensaje.frame(0));

                log.info("IN MSG [" + jsonMsg +  "] ZMTPSEssion( " + session.peerIdentity().getInt(0) + " )" + session);

                Gson gson =   new Gson();//new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
                VDLMsg vdlMSG = gson.fromJson(jsonMsg, VDLMsg.class);
                log.info("VDLMSG:" + vdlMSG);

                /* // Repetir el mensaje a todos los equipos conectados
                 for (final Peer peer : peersManager.peers()) {
                 log.info("PEER DEST " + peer.session().peerIdentity().getInt(0));
                 peer.send(mensaje);
                 } */
            } catch (Exception e) {
                log.error("handler threw exception", e);
            }
        }
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause)
            throws Exception {
        //log.warn("exception: " + cause);
        log.error("EXC", cause);
        ctx.close();
    }

    @Override
    public ZMTPSession session() {
        return session;
    }

    @Override
    public ListenableFuture<Void> send(final ZMTPMessage message) {
        log.info("SEND :" + message);
        // Es necesario generar un retain
        ZMTPMessage newMsg = message.retain();
        final ChannelFuture f = ch.writeAndFlush(newMsg);
        return listenable(f);
    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, final Object evt)
            throws Exception {
        if (evt instanceof ZMTPHandshakeSuccess) {
            peersManager.register(session.peerIdentity(), this);
            try {
                handler.connected(this);
            } catch (Exception e) {
                log.error("handler threw exception", e);
            }
        }
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        peersManager.deregister(session.peerIdentity());
        try {
            handler.disconnected(this);
        } catch (Exception e) {
            log.error("handler threw exception", e);
        }
    }

}
