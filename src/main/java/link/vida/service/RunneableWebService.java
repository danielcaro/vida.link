/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import link.vida.ws.WebServiceLogin;
import link.vida.ws.WebServiceVDL;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author dcaro
 */
public class RunneableWebService implements Runnable {

    @Override
    public void run() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(88);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // http://www.javacodegeeks.com/2015/03/creating-web-services-and-a-rest-server-with-jax-rs-and-jetty.html
        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                        "jersey.config.server.provider.packages",
                        "link.vida.ws");

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception ex) {
            Logger.getLogger(RunneableWebService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            jettyServer.destroy();
        }
    }

}
