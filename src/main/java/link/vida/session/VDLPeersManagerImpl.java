/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VDLPeersManagerImpl implements PeersManager {

    private static final Logger log = LoggerFactory.getLogger(VDLPeersManagerImpl.class);

    //Inmutable: que no pueden ser cambiados después de creados
    // Volatile: Que será comártido entre las hebras .
    private volatile List<VDLPeer> peers = ImmutableList.of();
    private volatile Map<Integer, VDLPeer> routing = ImmutableMap.of();
    // El lock del syncronized , debe ser final para ser compartido entre las hebras 
    private final Object lock = new Object();

    // Se crea un nuevo lista de datos usando el origen anterior.
    /**
     *
     * @param identity
     * @param peer
     */
    @Override
    public void register(final Integer identity, final VDLPeer peer) {

        synchronized (lock) {
            log.info("Register:" + identity);
            peers = ImmutableList.<VDLPeer>builder()
                    .addAll(peers)
                    .add(peer)
                    .build();
            routing = ImmutableMap.<Integer, VDLPeer>builder()
                    .putAll(routing)
                    .put(identity, peer)
                    .build();
        }
    }

    @Override
    public  void deregister(final Integer identity) {
        synchronized (lock) {
            log.info("Try DesRegister:" + identity);
            final ImmutableList.Builder<VDLPeer> newPeers = ImmutableList.builder();
            for (final VDLPeer peer : peers) {
                if (!peer.session().peerIdentity().equals(identity)) {                    
                    newPeers.add(peer);
                }else{
                    log.info("DesRegister:" + identity);
                }
            }
            peers = newPeers.build();

            final ImmutableMap.Builder<Integer, VDLPeer> newRouting = ImmutableMap.builder();
            final Integer id = identity;
            for (final Map.Entry<Integer, VDLPeer> entry : routing.entrySet()) {
                if (!entry.getKey().equals(id)) {
                    newRouting.put(entry.getKey(), entry.getValue());
                }
            }
            routing = newRouting.build();
        }
    }

    @Override
    public List<VDLPeer> peers() {
        return peers;
    }
}
