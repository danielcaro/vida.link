/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.app;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import link.vida.db.Migrator;
import link.vida.security.VDLAuthConfiguration;
import link.vida.security.CallbackHandlerConsole;
import link.vida.security.CallbackHandlerVDL;
import link.vida.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VidaLink {

    /**
     * @param args the command line arguments
     */
    private static final Logger log = LoggerFactory.getLogger(VidaLink.class);

    public static Injector injector;

    /**
     *
     * @return
     */
    protected Injector getInjector() {
        startInjector();
        return injector;
    }

    private void startInjector() {
        if (injector == null) {
            setInjector(Guice.createInjector(Stage.PRODUCTION, new Module[]{new LoaderModule()}));
        }
    }

    private static void setInjector(Injector injectorNew) {
        injector = injectorNew;
    }

    public static void main(String[] args)
            throws InterruptedException, Exception {
        // TODO: Avoid multiple instances  http://www.rgagnon.com/javadetails/java-0288.html
        // TODO: Check Postgres before start. http://stackoverflow.com/questions/18756113/how-to-test-connection-to-oracle-database-using-java
        new Migrator().run();
        new VidaLink().startInjector();
        Utils.showBindings(injector);
        ServiceManagerImpl sManager = (ServiceManagerImpl) injector.getInstance(ServiceManagerImpl.class);
        sManager.start();
    }

}
