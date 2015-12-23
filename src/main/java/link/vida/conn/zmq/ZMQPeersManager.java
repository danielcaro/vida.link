/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.spotify.netty4.handler.codec.zmtp.ZMTPHandshakeSuccess;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class ZMQPeersManager implements PeersManager {

    private static final Logger log = LoggerFactory.getLogger(ZMQPeersManager.class);

    //Inmutable: que no pueden ser cambiados después de creados
    // Volatile: Que será comártido entre las hebras .
    private volatile List<ZMQPeer> peers = ImmutableList.of();
    private volatile Map<ByteBuf, ZMQPeer> routing = ImmutableMap.of();
    // El lock del syncronized , debe ser final para ser compartido entre las hebras 
    private final Object lock = new Object();

    // Se crea un nuevo lista de datos usando el origen anterior.
    /**
     *
     * @param identity
     * @param peer
     */
    @Override
    public void register(final ByteBuffer identity, final ZMQPeerImpl peer) {

        synchronized (lock) {
            log.info("Register:" + identity.getInt(0));
            peers = ImmutableList.<ZMQPeer>builder()
                    .addAll(peers)
                    .add(peer)
                    .build();
            routing = ImmutableMap.<ByteBuf, ZMQPeer>builder()
                    .putAll(routing)
                    .put(Unpooled.wrappedBuffer(identity), peer)
                    .build();
        }
    }

    @Override
    public  void deregister(final ByteBuffer identity) {
        synchronized (lock) {
            log.info("Try DesRegister:" + identity.getInt(0));
            final ImmutableList.Builder<ZMQPeer> newPeers = ImmutableList.builder();
            for (final ZMQPeer peer : peers) {
                if (peer.session().peerIdentity().getInt(0) != identity.getInt(0)) {                    
                    newPeers.add(peer);
                }else{
                    log.info("DesRegister:" + identity.getInt(0));
                }
            }
            peers = newPeers.build();

            final ImmutableMap.Builder<ByteBuf, ZMQPeer> newRouting = ImmutableMap.builder();
            final ByteBuf id = Unpooled.wrappedBuffer(identity);
            for (final Map.Entry<ByteBuf, ZMQPeer> entry : routing.entrySet()) {
                if (!entry.getKey().equals(id)) {
                    newRouting.put(entry.getKey(), entry.getValue());
                }
            }
            routing = newRouting.build();
        }
    }

    @Override
    public List<ZMQPeer> peers() {
        return peers;
    }
}
