/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.google.inject.Inject;
import link.vida.conn.ConnService;
import link.vida.msgs.VDLMsgData;
import org.slf4j.LoggerFactory;
import link.vida.conn.socketio.listeners.VDLMsgDataListener;

/**
 *
 * @author dcaro
 */
public class ConnSocketIO extends Thread implements ConnService {

    private  final org.slf4j.Logger log = LoggerFactory.getLogger(ConnSocketIO.class);
    private SocketIOServer server;
    private Integer connectorId;
    
    @Inject
    VDLMsgDataListener msgDataListener;
    
    @Inject
    SocketIOServer socketIOServer;
    
    

    @Override
    public void run() {                                      
        socketIOServer.addEventListener("vdlMsgData", VDLMsgData.class, msgDataListener);
        socketIOServer.start();
    }

    @Override
    public void interrupt() {
        socketIOServer.stop();
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
