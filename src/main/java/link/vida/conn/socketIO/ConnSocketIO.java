/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketIO;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import link.vida.conn.ConnService;
import link.vida.msgs.ChatObject;
import link.vida.msgs.VDLMsgData;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class ConnSocketIO extends Thread implements ConnService {

    private  final org.slf4j.Logger log = LoggerFactory.getLogger(ConnSocketIO.class);
    private SocketIOServer server;
    private Integer connectorId;


    @Override
    public void run() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9090);

        server = new SocketIOServer(config);

        server.addEventListener("chatevent", ChatObject.class, new DataListener<ChatObject>() {
            @Override
            public void onData(SocketIOClient client, ChatObject data, AckRequest ackRequest) {
                log.info("MSG" + data);
                server.getBroadcastOperations().sendEvent("chatevent", data);
            }
        });
        
        server.addEventListener("vdlMsgData", VDLMsgData.class, new VDLMsgDataListener(server));

        server.start();
    }

    @Override
    public void interrupt() {
        server.stop();
    }

    @Override
    public String getConnName() {
        return "CONN.SOCKET-IO";
    }

    @Override
    public Integer getConnectorId() {
        return connectorId;
    }

    @Override
    public void setConnectorId(Integer connectorId) {
        this.connectorId = connectorId;
    }

}
