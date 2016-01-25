/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.google.inject.Inject;
import link.vida.data.bus.VDLInbox;
import link.vida.db.vdl.VdlDao;
import link.vida.msgs.VDLAck;
import link.vida.msgs.VDLMsgData;
import org.slf4j.LoggerFactory;
import rx.Observable;

/**
 *
 * @author dcaro
 */
public class VDLMsgDataListenerImpl implements VDLMsgDataListener<VDLMsgData> {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(VDLMsgDataListenerImpl.class);
//    private final SocketIOServer server;

    @Inject
    VdlDao vdlDao;

    @Inject
    SocketIOServer server;

    @Inject
    VDLInbox vDLInbox;

    @Override
    public void onData(SocketIOClient client, VDLMsgData data, AckRequest ackRequest) throws Exception {
//        log.info("MSG" + data);
        vDLInbox.rcvMsg(data).subscribe(
                response -> server.getBroadcastOperations().sendEvent("vdlMsgData", data),
                error -> log.info("Error: " + error.getMessage()));
        //server.getBroadcastOperations().sendEvent("vdlMsgData", data);       
    }

}
