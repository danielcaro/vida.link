/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin;

import com.google.inject.AbstractModule;


/**
 *
 * @author Xein
 */
public class AdminConnectorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AdminConnectorManager.class).to(AdminConnectorManagerImpl.class).asEagerSingleton();
    }
    
}
