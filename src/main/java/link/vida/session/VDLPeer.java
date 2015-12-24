/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import link.vida.conn.zmq.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;
import com.spotify.netty4.handler.codec.zmtp.ZMTPSession;
import link.vida.msgs.VDLMsg;

/**
 *
 * @author dcaro
 */
public interface VDLPeer {

    /**
     * Get the VDL session for this peer.
     * @return 
     */
    VDLSession session();
    
    
    Integer getPeerId();

    /**
     * Send a message to this peer.
     * @param message
     * @return 
     */
    ListenableFuture<Void> send(VDLMsg message);
}
