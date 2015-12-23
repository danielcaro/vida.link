/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn;

import com.google.inject.AbstractModule;


/**
 *
 * @author Xein
 */
public class ConnectorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ConnectorManager.class).to(ConnectorManagerImpl.class).asEagerSingleton();
    }
    
}
