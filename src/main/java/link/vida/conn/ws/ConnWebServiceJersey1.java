/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.ws;

import com.google.inject.servlet.GuiceFilter;
import com.thetransactioncompany.cors.CORSFilter;
import java.net.URI;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.LoggerFactory;
import link.vida.conn.Connector;

/**
 *
 * @author dcaro
 */
public class ConnWebServiceJersey1 extends Thread implements Connector {

    // https://github.com/piersy/jersey2-guice-example-with-test/blob/master/src/test/java/EmbeddedJetty.java
    // guice ws 
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(ConnWebServiceJersey1.class);
    private Server jettyServer;

    // option
    // https://gist.github.com/dalegaspi/5060b04326965ea704ea
    @Override
    public void run() {
        jettyServer = new Server(88);

        ServletContextHandler context = new ServletContextHandler(jettyServer, "/", ServletContextHandler.SESSIONS);
        context.addFilter(CORSFilter.class, "/*", EnumSet.<javax.servlet.DispatcherType>of(DispatcherType.INCLUDE, DispatcherType.REQUEST));
        context.addFilter(GuiceFilter.class, "/*", EnumSet.<javax.servlet.DispatcherType>of(javax.servlet.DispatcherType.REQUEST, javax.servlet.DispatcherType.ASYNC));
        context.addServlet(DefaultServlet.class, "/*");

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception ex) {
            Logger.getLogger(ConnWebServiceJersey1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            jettyServer.destroy();
        }
    }

    @Override
    public void interrupt() {

        try {
            jettyServer.stop();
        } catch (Exception ex) {
            Logger.getLogger(ConnWebServiceJersey1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getConnectorName() {
        return "CONN.WS";
    }

}
