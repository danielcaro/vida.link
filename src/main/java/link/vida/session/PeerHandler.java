/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import link.vida.msgs.VDLMsg;

/**
 *
 * @author dcaro
 */
public interface PeerHandler {

    /**
     * A peer connected.
     */
    void connected(VDLPeer peer);

    /**
     * A peer disconnected.
     */
    void disconnected(VDLPeer peer);

    /**
     * A message was received from a peer.
     */
    void message(VDLPeer peer, VDLMsg message);
}
