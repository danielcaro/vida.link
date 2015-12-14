/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;
/**
 *
 * @author dcaro
 */
public class RunneableJSF implements Runnable{

    @Override
    public void run() {
        try {
            Server server = new Server(8080);
            
            WebAppContext wac = new AliasEnhancedWebAppContext();
            wac.setContextPath("/myapp");
            wac.setBaseResource(
                    new ResourceCollection(
                            new String[] {"./src/main/webapp", "./target"}));
            wac.setResourceAlias("/WEB-INF/classes/", "/classes/");
            
//            wac.addEventListener(new Listener());        
            //wac.setInitParameter("useFileMappedBuffer", "false");
            server.setHandler(wac);
            server.setStopAtShutdown(true);
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(RunneableJSF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
