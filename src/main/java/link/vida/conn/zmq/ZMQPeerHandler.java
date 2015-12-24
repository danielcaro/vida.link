/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;

/**
 *
 * @author dcaro
 */
public interface ZMQPeerHandler {

    /**
     * A peer connected.
     */
    void connected(ZMQPeer peer);

    /**
     * A peer disconnected.
     */
    void disconnected(ZMQPeer peer);

    /**
     * A message was received from a peer.
     */
    void message( ZMQPeer peer, ZMTPMessage message);
}
