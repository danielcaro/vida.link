/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.app;

import com.google.inject.Binding;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import link.vida.db.Migrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VidaLink extends GuiceServletContextListener{

    /**
     * @param args the command line arguments
     */
    private static final Logger log = LoggerFactory.getLogger(VidaLink.class);
    
    private static Injector injector;

    /**
     *
     * @return
     */
    @Override
    protected  Injector getInjector() {
        return injector;
    }

    private static void setInjector(Injector injectorNew) {
        injector = injectorNew;
    }

    private static void showBindings() {
        final Map<Key<?>, Binding<?>> map = injector.getBindings();
        List<Key<?>> keys = new ArrayList<>(map.keySet());
        keys.stream().forEach(new Consumer<Key<?>>() {
            @Override
            public void accept(Key<?> key) {
              log.info(key.toString() + ": " + map.get(key));
            }
        });
    }

    public static void main(String[] args)
            throws InterruptedException, Exception {
        
        new  Migrator().run();                
        setInjector(Guice.createInjector(Stage.PRODUCTION, new Module[]{new LoaderModule()}));
        showBindings();
        
        ServiceManager sManager = (ServiceManager)injector.getInstance(ServiceManager.class);
        sManager.start();        

    }

}
