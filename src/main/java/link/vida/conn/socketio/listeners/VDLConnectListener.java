/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio.listeners;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VDLConnectListener implements ConnectListener{
    
    private final org.slf4j.Logger log = LoggerFactory.getLogger(VDLMsgDataListenerImpl.class);

    @Override
    public void onConnect(SocketIOClient sioc) {
        log.info("CONNECTED"+ sioc.getSessionId());
    }


}
