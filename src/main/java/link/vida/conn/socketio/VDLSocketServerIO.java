/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.google.inject.Inject;

/**
 *
 * @author dcaro
 */
public class VDLSocketServerIO extends SocketIOServer{
    
    
    
    @Inject
    public VDLSocketServerIO(Configuration configuration) {
        super(configuration);
    }
        
    
}
