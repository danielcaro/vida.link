/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import com.google.inject.Singleton;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.webapp.FacesServlet;
import link.vida.admin.AdminConnector;
import link.vida.app.VidaLink;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author dcaro
 */
@Singleton
public class AdminConnectorJSF extends Thread implements AdminConnector {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(VidaLink.class);

    @Override
    public String getConnectorName() {
        return "ADMIN.JSF";
    }

    @Override
    public void run() {
        try {
            Server server = new Server(8080);
            // http://musingsofaprogrammingaddict.blogspot.cl/2009/12/running-jsf-2-on-embedded-jetty.html
            // http://www.javacodegeeks.com/2013/01/lightweight-web-application-primefaces-jsf-guice-mybatis-part-1.html

            final String webappDir = this.getClass().getClassLoader().getResource("webapp").toExternalForm();
            log.info("WEBAPP DIR :" + webappDir);

            WebAppContext wac = new AliasEnhancedWebAppContext(webappDir, "/");
            wac.setContextPath("/");

            // http://forum.primefaces.org/viewtopic.php?f=10&t=25025
            // Ver la forma que llame dentro del mismo JAR.
            if (webappDir.contains(".jar")) {
                wac.setBaseResource(new ResourceCollection(new String[]{webappDir, "."}));
//                wac.setResourceAlias("/WEB-INF/classes/", "");
            } else {
                wac.setBaseResource(new ResourceCollection(new String[]{webappDir, "./target"}));
//                wac.setResourceAlias("/WEB-INF/classes/", "/classes/");
            }
            

            wac.setDisplayName("vida.link web");
            server.setHandler(wac);
            server.setStopAtShutdown(true);
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(AdminConnectorJSF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void configurateWebXML(WebAppContext context) {
//        log.info("Initializing JSF programmatically ...");
//        
//        // Add JSF Listener for initialization ...
//        context.addEventListener(new org.apache.myfaces.webapp.StartupServletContextListener());
//        
//        // JSF parameters ...
//        
//        context.setInitParameter("com.sun.faces.forceLoadConfiguration", "true");
//        context.setInitParameter("com.sun.faces.enableRestoreView11Compatibility", "true");
//
//        context.setInitParameter("javax.faces.PROJECT_STAGE", "Production");
//        context.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "0");
//        context.setInitParameter("facelets.DEVELOPMENT", "false");
////        context.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
//        context.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
////        context.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
//
////        context.setInitParameter("defaultHtmlEscape", "true");
//
//        context.setInitParameter("primefaces.THEME", "bootstrap");
////        context.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "false");
//
//        context.setInitParameter("net.bootsfaces.get_fontawesome_from_cdn", "false");
//
//        // JSF Servlet ...
//        ServletHolder jsfServlet = new ServletHolder(FacesServlet.class);
//        jsfServlet.setDisplayName("Faces Servlet");
//        jsfServlet.setName("Faces_Servlet");
//        jsfServlet.setInitOrder(0);
//      
//        // Add to web context ...
//        context.addServlet(jsfServlet, "*.xhtml");                   
////        context.addServlet(jsfServlet, "/javax.faces.resource/*");                   
//        
//        context.setWelcomeFiles(new String[]{"index.xhtml"});        
//
//    }
    ///https://github.com/stefoan/primefaces-jetty/blob/master/src/main/java/de/stefanlindenberg/web/pfjetty/StandaloneJetty.java
//    @Override
//    public void run() {
//        int port = 8080;
//
//        try {
//            // FIXME remove this
//            StandAloneJettyJSF.displayTrayIcon(port);
//        } catch (Exception ex) {
//            Logger.getLogger(AdminConnectorJSF.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        StandAloneJettyJSF embeddedServer = new StandAloneJettyJSF(port);
//        embeddedServer.listen();
//    }
//
//    @Override
//    public void interrupt() {
//
//    }
}
