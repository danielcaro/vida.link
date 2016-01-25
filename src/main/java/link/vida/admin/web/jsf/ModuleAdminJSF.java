/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import com.google.inject.multibindings.Multibinder;
import com.google.inject.servlet.ServletModule;
import link.vida.admin.AdminConnector;

/**
 *
 * @author dcaro
 */
public class ModuleAdminJSF extends ServletModule {

    @Override
    protected void configureServlets() {
        Multibinder<AdminConnector> uriBinder = Multibinder.newSetBinder(binder(), AdminConnector.class);
        uriBinder.addBinding().to(AdminConnectorJSF.class );
    }

}
