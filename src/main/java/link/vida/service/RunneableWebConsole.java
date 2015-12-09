/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.service;

import link.vida.webconsole.VDLinkApplication;
import org.apache.wicket.protocol.http.ContextParamWebApplicationFactory;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.protocol.http.WicketServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author dcaro
 */
public class RunneableWebConsole implements Runnable {

    @Override
    public void run() {
        // https://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty
        // https://cwiki.apache.org/confluence/pages/viewpage.action?pageId=27848170
        Server server = new Server(84);
        /* Setup server (port, etc.) */
        ServletContextHandler sch = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHolder sh = new ServletHolder(WicketServlet.class);
        sh.setInitParameter(ContextParamWebApplicationFactory.APP_CLASS_PARAM,
                VDLinkApplication.class.getName());
        sh.setInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
//        boolean DEV_MODE = true;
//        /* Define a variable DEV_MODE and set to false
//         * if wicket should be used in deployment mode
//         */
//        if (!DEV_MODE) {
//            sh.setInitParameter("wicket.configuration", "deployment");
//        }

//        String staticPath = this.getClass().getClassLoader().getResource("static/").toExternalForm();
//        ServletHolder resourceServlet = new ServletHolder(DefaultServlet.class);
//        resourceServlet.setInitParameter("dirAllowed", "true");
//        resourceServlet.setInitParameter("resourceBase", staticPath);
//        resourceServlet.setInitParameter("pathInfoOnly", "true");
        sch.addServlet(sh, "/*");
        sch.setResourceBase(".");
        server.setHandler(sch);

        try {
            System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
            server.start();
            //System.in.read();
            //System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
            //server.stop();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
