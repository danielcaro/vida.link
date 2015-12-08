/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vidaware.vida.link;

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
public class VDLPeersManager implements PeersManager {

    private static final Logger log = LoggerFactory.getLogger(VDLPeersManager.class);

    //Inmutable: que no pueden ser cambiados después de creados
    // Volatile: Que será comártido entre las hebras .
    private volatile List<Peer> peers = ImmutableList.of();
    private volatile Map<ByteBuf, Peer> routing = ImmutableMap.of();
    // El lock del syncronized , debe ser final para ser compartido entre las hebras 
    private final Object lock = new Object();

    // Se crea un nuevo lista de datos usando el origen anterior.
    /**
     *
     * @param identity
     * @param peer
     */
    @Override
    public void register(final ByteBuffer identity, final VDLPeer peer) {

        synchronized (lock) {
            log.info("Register:" + identity.getInt(0));
            peers = ImmutableList.<Peer>builder()
                    .addAll(peers)
                    .add(peer)
                    .build();
            routing = ImmutableMap.<ByteBuf, Peer>builder()
                    .putAll(routing)
                    .put(Unpooled.wrappedBuffer(identity), peer)
                    .build();
        }
    }

    @Override
    public  void deregister(final ByteBuffer identity) {
        synchronized (lock) {
            log.info("DesRegister:" + identity.getInt(0));
            final ImmutableList.Builder<Peer> newPeers = ImmutableList.builder();
            for (final Peer peer : peers) {
                if (peer != this) {
                    newPeers.add(peer);
                }
            }
            peers = newPeers.build();

            final ImmutableMap.Builder<ByteBuf, Peer> newRouting = ImmutableMap.builder();
            final ByteBuf id = Unpooled.wrappedBuffer(identity);
            for (final Map.Entry<ByteBuf, Peer> entry : routing.entrySet()) {
                if (!entry.getKey().equals(id)) {
                    newRouting.put(entry.getKey(), entry.getValue());
                }
            }
            routing = newRouting.build();
        }
    }

    @Override
    public List<Peer> peers() {
        return peers;
    }
}
