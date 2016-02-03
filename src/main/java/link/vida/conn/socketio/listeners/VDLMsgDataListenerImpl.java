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
import rx.Observer;
import rx.functions.Action1;

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
        log.info("MSG DATA FROM " + client.getSessionId());
//        log.info("MSG" + data);
// http://stackoverflow.com/questions/20417569/acknowledgment-for-socket-io-custom-event ACK
        vDLInbox.rcvMsg(data).subscribe(
                new Observer<VDLAck>() {
            @Override
            public void onCompleted() {
                log.info("onComplete");
            }

            @Override
            public void onError(Throwable e) {
                log.info("Error: " + e.getMessage());
            }

            @Override
            public void onNext(VDLAck ack) {
                if (ackRequest.isAckRequested()) {
                    ackRequest.sendAckData(ack);
                }
                server.getClient(client.getSessionId()).sendEvent("vdlMsgData", data);
//                server.getBroadcastOperations().sendEvent("vdlMsgData", data);
            }

        });

    }
}
