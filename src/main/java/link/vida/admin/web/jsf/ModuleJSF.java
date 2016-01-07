/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import link.vida.admin.AdminConnService;

/**
 *
 * @author dcaro
 */
public class ModuleJSF extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(AdminConnService.class).annotatedWith(Names.named("ADMIN.JSF")).to(AdminConnServiceJSF.class).asEagerSingleton();
    }

}
