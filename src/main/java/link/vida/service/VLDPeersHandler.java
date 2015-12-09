/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.service;

import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VLDPeersHandler implements Handler {

    private static final Logger log = LoggerFactory.getLogger(VLDPeersHandler.class);

    @Override
    public void connected(Peer peer) {
        log.info("CONNECTED");
    }

    @Override
    public void disconnected(Peer peer) {
        log.info("DISCONNECTED");
    }

    @Override
    public void message(Peer peer, ZMTPMessage message) {
        log.info("MESSAGE");
    }

}
