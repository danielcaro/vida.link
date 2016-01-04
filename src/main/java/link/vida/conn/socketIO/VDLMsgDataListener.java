/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketIO;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import link.vida.msgs.VDLMsgData;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VDLMsgDataListener implements DataListener<VDLMsgData> {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(VDLMsgDataListener.class);
    private SocketIOServer server;

    public VDLMsgDataListener(SocketIOServer server) {
        this.server = server;
    }
    
    

    @Override
    public void onData(SocketIOClient client, VDLMsgData data, AckRequest ackRequest) throws Exception {
        log.info("MSG" + data);
        server.getBroadcastOperations().sendEvent("chatevent", data);
    }

}
