/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class ZMQPeersHandler implements Handler {

    private static final Logger log = LoggerFactory.getLogger(ZMQPeersHandler.class);

    @Override
    public void connected(ZMQPeer peer) {
        log.info("CONNECTED");
    }

    @Override
    public void disconnected(ZMQPeer peer) {
        log.info("DISCONNECTED");
    }

    @Override
    public void message(ZMQPeer peer, ZMTPMessage message) {
        log.info("MESSAGE");
    }

}
