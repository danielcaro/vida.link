package link.vida.conn.socketio;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.google.inject.Inject;
import link.vida.msgs.VDLMsgData;
import org.slf4j.LoggerFactory;
import link.vida.conn.socketio.listeners.VDLMsgDataListener;
import link.vida.conn.Connector;

/**
 *
 * @author dcaro
 */
public class ConnSocketIO extends Thread implements Connector {

    private final org.slf4j.Logger log = LoggerFactory.getLogger(ConnSocketIO.class);

    @Inject
    VDLMsgDataListener msgDataListener;
    
    @Inject 
    ConnectListener connectListener;

    @Inject
    SocketIOServer socketIOServer;

    @Override
    public void run() {        
        socketIOServer.addEventListener("vdlMsgData", VDLMsgData.class, msgDataListener);
        socketIOServer.addConnectListener(connectListener);
        socketIOServer.start();
    }

    @Override
    public void interrupt() {
        socketIOServer.stop();
    }

    @Override
    public String getConnectorName() {
        return "CONN.SOCKET-IO";
    }

}
