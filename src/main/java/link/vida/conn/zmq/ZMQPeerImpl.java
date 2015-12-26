/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import link.vida.session.VDLPeerHandlerImpl;
import link.vida.session.PeerHandler;
import com.google.common.util.concurrent.ListenableFuture;
import com.spotify.netty4.handler.codec.zmtp.ZMTPHandshakeSuccess;
import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;
import com.spotify.netty4.handler.codec.zmtp.ZMTPSession;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.ArrayList;
import java.util.List;
import link.vida.app.VidaLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static link.vida.conn.zmq.ListenableFutureAdapter.listenable;
import link.vida.msgs.VDLDecoder;
import link.vida.msgs.VDLMsg;
import link.vida.msgs.VDLTest;
import link.vida.session.VDLPeersManager;
import link.vida.session.VDLPeer;
import link.vida.session.VDLSession;

/**
 *
 * @author dcaro
 */
public class ZMQPeerImpl extends ChannelInboundHandlerAdapter implements VDLPeer {

    private static final Logger log = LoggerFactory.getLogger(ZMQPeerImpl.class);

    private final Channel ch;
    private final ZMTPSession session;
    private final PeerHandler handler;
    private final VDLPeersManager peersManager;

    public ZMQPeerImpl(final Channel ch, final ZMTPSession session, VDLPeersManager peersManager) {
        this.ch = ch;
        this.session = session;
        this.handler = new VDLPeerHandlerImpl();
        VidaLink.injector.injectMembers(this.handler);
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

                // TODO : iterar sobre los mensajes ..
                String jsonMsg = toString(mensaje.frame(0));

                log.info("IN MSG [" + jsonMsg + "] ZMTPSEssion( " + session.peerIdentity().getInt(0) + " )" + session);

                Object obj = VDLDecoder.getObject(jsonMsg);

                if (obj != null) {
                     if (obj instanceof VDLMsg) {
                        // Repetir el mensaje a todos los equipos conectados
                        for (final VDLPeer peer : peersManager.peers()) {
//                            if (!peer.getPeerId().equals(this.getPeerId())) {
                                log.info("PEER DEST " + peer.getPeerId());
                                // transformar mensaje  de ZMQMessage to VLDMsg
                                peer.send((VDLMsg) obj);
//                            }
                        }
                    }else if (obj instanceof VDLTest) {
                        log.info(((VDLTest) obj).toString());
                    } 

                    // Reflection
                    String className = obj.getClass().getName();
                    switch (className.substring(className.lastIndexOf('.') + 1)) {
                        case "VDLTest":
                            log.info(((VDLTest) obj).toString());
                            break;
                        default:
                            log.info("class:" + className);
                    }
                }

            } catch (Exception e) {
                log.error("handler threw exception", e);
            }
        }
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause)
            throws Exception {
        //log.warn("exception: " + cause);
        log.error("exceptionCaught" + cause.toString());
        ctx.close();
    }

//    @Override
//    public VDLSession session() {
//        // Castear sesion a otra
//        return (VDLSession) session;
//    }

    @Override
    public ListenableFuture<Void> send(final VDLMsg message) {
        log.info("SEND :" + message);
        // Es necesario generar un retain
        // crear nuevo mensaje
        // Generar Encoder y Decoder de VDLMsg a ZMQ message
        List<ByteBuf> frames = new ArrayList<>();
        ZMTPMessage newMsg = ZMTPMessage.from(frames);
//        ZMTPMessage newMsg = message.retain();
        final ChannelFuture f = ch.writeAndFlush(newMsg);
        return listenable(f);
    }

    @Override
    public void userEventTriggered(final ChannelHandlerContext ctx, final Object evt)
            throws Exception {
        if (evt instanceof ZMTPHandshakeSuccess) {
            peersManager.register(session.peerIdentity().getInt(0), this);
            try {
                handler.connected(this);
            } catch (Exception e) {
                log.error("handler threw exception", e);
            }
        }
    }

    @Override
    public void channelInactive(final ChannelHandlerContext ctx) throws Exception {
        peersManager.deregister(session.peerIdentity().getInt(0));
        try {
            handler.disconnected(this);
        } catch (Exception e) {
            log.error("handler threw exception", e);
        }
    }

    @Override
    public Integer getPeerId() {
        return this.session.peerIdentity().getInt(0);
    }

}
