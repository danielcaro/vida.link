/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.data.bus;

import com.google.inject.servlet.ServletModule;

/**
 *
 * @author dcaro
 */
public class DataBusModule extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(VDLInbox.class).to(VDLInboxImpl.class).asEagerSingleton();
    }

}
