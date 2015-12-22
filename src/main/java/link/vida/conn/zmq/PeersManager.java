/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import java.nio.ByteBuffer;
import java.util.List;

/**
 *
 * @author dcaro
 */
public interface PeersManager {

    public void register(final ByteBuffer identity, final VDLPeer peer);

    public void deregister(final ByteBuffer identity);

    public List<Peer> peers();

}
