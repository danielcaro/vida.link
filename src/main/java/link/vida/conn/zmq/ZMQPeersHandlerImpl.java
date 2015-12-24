/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.inject.Inject;
import com.spotify.netty4.handler.codec.zmtp.ZMTPMessage;
import link.vida.db.vdl.VdlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class ZMQPeersHandlerImpl implements ZMQPeerHandler {

    private static final Logger log = LoggerFactory.getLogger(ZMQPeersHandlerImpl.class);
    
    @Inject
    VdlDao vdlDao;

    @Override
    public void connected(ZMQPeer peer) {
        log.info("CONNECTED");
        vdlDao.peersList();
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
