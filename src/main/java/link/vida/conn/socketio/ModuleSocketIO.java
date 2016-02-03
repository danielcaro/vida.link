/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.servlet.ServletModule;
import link.vida.conn.socketio.listeners.VDLMsgDataListenerImpl;
import link.vida.conn.socketio.listeners.VDLMsgDataListener;
import link.vida.conn.Connector;
import link.vida.conn.socketio.listeners.VDLConnectListener;

/**
 *
 * @author dcaro
 */
public class ModuleSocketIO extends ServletModule {

    @Override
    protected void configureServlets() {
        Multibinder<Connector> uriBinder = Multibinder.newSetBinder(binder(), Connector.class);
        uriBinder.addBinding().to(ConnSocketIO.class);

        bind(Configuration.class).to(VDLSocketIOConfig.class).asEagerSingleton();
        bind(SocketIOServer.class).to(VDLSocketServerIO.class).asEagerSingleton();
        bind(VDLMsgDataListener.class).to(VDLMsgDataListenerImpl.class).asEagerSingleton();
        bind(ConnectListener.class).to(VDLConnectListener.class).asEagerSingleton();
    }

}
