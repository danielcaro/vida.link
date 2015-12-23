/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import io.netty.channel.ChannelInitializer;
import link.vida.conn.ConnService;
import link.vida.conn.ws.ConnWebServiceJersey1;

/**
 *
 * @author dcaro
 */
public class ModuleZMQ extends AbstractModule{

    @Override
    protected void configure() {        
        bind(PeersManager.class).to(ZMQPeersManager.class).asEagerSingleton();
        bind(ChannelInitializer.class).to(ZMQChInit.class).asEagerSingleton();
        bind(ConnService.class).annotatedWith(Names.named("CONN.ZMQ")).to(ConnZMQService.class).asEagerSingleton();
    }
    
}
