/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import com.spotify.netty4.handler.codec.zmtp.ZMTPIdentityGenerator;
import io.netty.channel.ChannelInitializer;
import link.vida.conn.Connector;

/**
 *
 * @author dcaro
 */
public class ModuleZMQ extends AbstractModule {

    @Override
    protected void configure() {
        bind(ZMTPIdentityGenerator.class).to(ZMQIdentityGenerator.class).asEagerSingleton();
        bind(ChannelInitializer.class).to(ZMQChInit.class).asEagerSingleton();
        Multibinder<Connector> uriBinder = Multibinder.newSetBinder(binder(), Connector.class);
        uriBinder.addBinding().to(ConnZMQService.class);
    }

}
