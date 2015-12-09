/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.service;

import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;

/**
 *
 * @author dcaro
 */
public interface Handler {

    /**
     * A peer connected.
     */
    void connected(Peer peer);

    /**
     * A peer disconnected.
     */
    void disconnected(Peer peer);

    /**
     * A message was received from a peer.
     */
    void message( Peer peer, ZMTPMessage message);
}
