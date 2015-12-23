/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import link.vida.conn.ws.*;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import java.util.HashMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import link.vida.admin.AdminConnService;
import link.vida.conn.ConnService;
import link.vida.conn.ws.services.WebServiceVDL;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.eclipse.jetty.servlet.DefaultServlet;

/**
 *
 * @author dcaro
 */
public class ModuleJSF extends ServletModule {

    @Override
    protected void configureServlets() {
        bind(AdminConnService.class).annotatedWith(Names.named("ADMIN.JSF")).to(AdminConnServiceJSF.class).asEagerSingleton();
            //https://hajix.wordpress.com/2014/08/07/starting-a-simple-server-with-jettyjerseyguicejackson-stack/ jersey 1
        // https://github.com/hajiz/jetty-jersey-guice-jackson

        // https://github.com/piersy/jersey2-guice-example-with-test/blob/master/src/test/java/EmbeddedJetty.java jersery 2
//        bind(DefaultServlet.class).in(Singleton.class);
//
//        // ingresar lista de resources, si no ingresa habrá error.
//        bind(WebServiceVDL.class).in(Singleton.class);
//
//        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
//        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);        
//
//        HashMap<String, String> options = new HashMap<>();
//        options.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
//        serve("/*").with(GuiceContainer.class, options);
    }

}
