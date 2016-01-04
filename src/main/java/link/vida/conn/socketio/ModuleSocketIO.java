/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import link.vida.conn.ConnService;
import link.vida.conn.socketio.listeners.VDLMsgDataListenerImpl;
import link.vida.conn.socketio.listeners.VDLMsgDataListener;

/**
 *
 * @author dcaro
 */
public class ModuleSocketIO extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(ConnService.class).annotatedWith(Names.named("CONN.SOCKET-IO")).to(ConnSocketIO.class).asEagerSingleton();
        
        bind(Configuration.class).to(VDLSocketIOConfig.class).asEagerSingleton();
        bind(SocketIOServer.class).to(VDLSocketServerIO.class).asEagerSingleton();
        bind(VDLMsgDataListener.class).to(VDLMsgDataListenerImpl.class).asEagerSingleton();
    }

}
