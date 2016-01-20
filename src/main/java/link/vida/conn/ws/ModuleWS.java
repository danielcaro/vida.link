/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.ws;

import com.google.inject.Singleton;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import java.util.HashMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import link.vida.conn.ws.services.WebServiceVDL;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.eclipse.jetty.servlet.DefaultServlet;
import link.vida.conn.Connector;
import link.vida.conn.socketio.ConnSocketIO;

/**
 *
 * @author dcaro
 */
public class ModuleWS extends ServletModule {

    @Override
    protected void configureServlets() {
        // https://hajix.wordpress.com/2014/08/07/starting-a-simple-server-with-jettyjerseyguicejackson-stack/ jersey 1
        // https://github.com/hajiz/jetty-jersey-guice-jackson
        // https://github.com/piersy/jersey2-guice-example-with-test/blob/master/src/test/java/EmbeddedJetty.java jersery 2

        Multibinder<Connector> uriBinder = Multibinder.newSetBinder(binder(), Connector.class);
        uriBinder.addBinding().to(ConnWebServiceJersey1.class);

        bind(DefaultServlet.class).in(Singleton.class);

        // ingresar lista de resources, si no ingresa habrá error.
        bind(WebServiceVDL.class).in(Singleton.class);

        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);

        HashMap<String, String> options = new HashMap<>();
        options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        serve("/*").with(GuiceContainer.class, options);
    }

}
