/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.common.util.concurrent.ListenableFuture;
import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;
import com.spotify.netty4.handler.codec.zmtp.ZMTPSession;

/**
 *
 * @author dcaro
 */
public interface Peer {

    /**
     * Get the ZMTP session for this peer.
     * @return 
     */
    ZMTPSession session();
    
    
    Integer getPeerId();

    /**
     * Send a message to this peer.
     * @param message
     * @return 
     */
    ListenableFuture<Void> send(ZMTPMessage message);
}
