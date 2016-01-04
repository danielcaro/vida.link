/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketIO;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import link.vida.conn.ConnService;

/**
 *
 * @author dcaro
 */
public class ModuleSocketIO extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(ConnService.class).annotatedWith(Names.named("CONN.SOCKET-IO")).to(ConnSocketIO.class).asEagerSingleton();
    }

}
