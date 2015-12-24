/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import com.google.inject.AbstractModule;

/**
 *
 * @author dcaro
 */
public class ModulePeerManager extends AbstractModule{

    @Override
    protected void configure() {    
        bind(VDLIdentityGenerator.class).to(VDLIdentityGeneratorImpl.class).asEagerSingleton();
        bind(VDLPeersManager.class).to(VDLPeersManagerImpl.class).asEagerSingleton();
    }
    
}
