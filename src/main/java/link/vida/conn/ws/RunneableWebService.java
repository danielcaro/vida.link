/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.ws;


import com.google.inject.Singleton;
import com.thetransactioncompany.cors.CORSFilter;
import java.util.EnumSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 *
 * @author dcaro
 */
@Singleton
public class RunneableWebService extends Thread {
    
    // http://randomizedsort.blogspot.cl/2011/05/using-guice-ified-jersey-in-embedded.html
    // guice ws 
    

    // option
    // https://gist.github.com/dalegaspi/5060b04326965ea704ea
    @Override
    public void run() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        
        context.addFilter(CORSFilter.class, "/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        Server jettyServer = new Server(88);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

        // http://www.javacodegeeks.com/2015/03/creating-web-services-and-a-rest-server-with-jax-rs-and-jetty.html
        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.packages",
                "link.vida.conn.ws.services");


        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception ex) {
            Logger.getLogger(RunneableWebService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            jettyServer.destroy();
        }
    }

//    @Override
//    public void run() {
//              final ResourceConfig rc =
//                new ResourceConfig().packages("com.yourpackage...jersey",
//                        "com.wordnik.swagger.jaxrs.listing")
//                .register(JacksonFeature.class)        // enable Jackson
//                .register(ResponseCorsFilter.class)    // enable CORS
//                .register(ApiListingResource.class)
//                .register(SwaggerSerializers.class);
//
//        // use Jetty
//        ServletContextHandler context
//                = new ServletContextHandler(ServletContextHandler.SESSIONS);
//
//        // the main REST service context
//        context.setContextPath("/");
//        Server jettyServer = new Server(1339);
//        jettyServer.setHandler(context);
//        ServletHolder jerseyServlet = new ServletHolder(new
//                org.glassfish.jersey.servlet.ServletContainer(rc));
//        jerseyServlet.setInitOrder(0);
//        context.addServlet(jerseyServlet, "/*");
//        context.addEventListener(new ServiceInitializer());
//
//        // swagger JSON
//        ServletHolder swaggerServlet = new ServletHolder(new JerseyJaxrsConfig());
//        swaggerServlet.setName("Jersey2Config");
//        swaggerServlet.setInitParameter("swagger.api.basepath", BASE_URI);
//        swaggerServlet.setInitParameter("api.version", "1.0.0");
//        swaggerServlet.setInitOrder(2);
//        context.addServlet(swaggerServlet, "");
//
//        jettyServer.start();
//        return jettyServer;
//    }

}
