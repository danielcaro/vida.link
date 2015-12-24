/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import com.google.inject.Inject;
import link.vida.db.vdl.VdlDao;
import link.vida.msgs.VDLMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VDLPeerHandlerImpl implements PeerHandler {

    private static final Logger log = LoggerFactory.getLogger(VDLPeerHandlerImpl.class);
    
    @Inject
    VdlDao vdlDao;

    @Override
    public void connected(VDLPeer peer) {
        log.info("CONNECTED");
//        vdlDao.peersList();
    }

    @Override
    public void disconnected(VDLPeer peer) {
        log.info("DISCONNECTED");
    }

    @Override
    public void message(VDLPeer peer, VDLMsg message) {
        log.info("MESSAGE");
    }

}
