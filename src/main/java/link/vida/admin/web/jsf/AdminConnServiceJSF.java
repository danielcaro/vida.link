/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import com.google.inject.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import link.vida.admin.AdminConnService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 *
 * @author dcaro
 */
@Singleton
public class AdminConnServiceJSF extends Thread implements AdminConnService{

    private Integer adminConnectorId;
    
    @Override
    public void run() {
        try {
            Server server = new Server(8080);
            // http://musingsofaprogrammingaddict.blogspot.cl/2009/12/running-jsf-2-on-embedded-jetty.html
            // http://www.javacodegeeks.com/2013/01/lightweight-web-application-primefaces-jsf-guice-mybatis-part-1.html
            
            WebAppContext wac = new AliasEnhancedWebAppContext();
            wac.setContextPath("/");
            
            wac.setBaseResource(
                    new ResourceCollection( new String[] {"./src/main/webapp", "./target"}));
            wac.setResourceAlias("/WEB-INF/classes/", "/classes/");
            
            server.setHandler(wac);
            server.setStopAtShutdown(true);
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(AdminConnServiceJSF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getAdminConnName() {
       return "ADMIN.JSF";
    }

    @Override
    public Integer getAdminConnectorId() {
       return adminConnectorId;
    }

    @Override
    public void setAdminConnectorId(Integer connectorId) {
        this.adminConnectorId = connectorId;
    }
    
}
