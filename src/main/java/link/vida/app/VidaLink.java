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
import link.vida.security.VDLCallbackHandler;
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

        // TODO: Avoid multiple instances 
        //http://www.rgagnon.com/javadetails/java-0288.html
        new Migrator().run();
        new VidaLink().startInjector();

        testLogin();

        Utils.showBindings(injector);

        ServiceManagerImpl sManager = (ServiceManagerImpl) injector.getInstance(ServiceManagerImpl.class);

        sManager.start();

    }

    public static void testLogin() {
        // Obtain a LoginContext, needed for authentication. Tell it
        // to use the LoginModule implementation specified by the
        // entry named "Sample" in the JAAS login configuration
        // file and to also use the specified CallbackHandler.
        LoginContext lc = null;
        try {
            lc = new LoginContext(VDLAuthConfiguration.APP_NAME, new VDLCallbackHandler());
        } catch (LoginException le) {
            System.err.println("Cannot create LoginContext (LoginException). "
                    + le.getMessage());
            System.exit(-1);
        } catch (SecurityException se) {
            System.err.println("Cannot create LoginContext (SecurityException). "
                    + se.getMessage());
            System.exit(-1);
        }

        // the user has 3 attempts to authenticate successfully
        int i;
        for (i = 0; i < 3; i++) {
            try {

                // attempt authentication
                lc.login();

                // if we return with no exception, authentication succeeded
                break;

            } catch (LoginException le) {

                System.err.println("Authentication failed:");
                System.err.println("  " + le.getMessage());
                try {
                    Thread.currentThread().sleep(3000);
                } catch (Exception e) {
                    // ignore
                }

            }
        }

        // did they fail three times?
        if (i == 3) {
            System.out.println("Sorry");
            System.exit(-1);
        }

        System.out.println("Authentication succeeded!");
    }

    public static void genJKS() {
        // http://www.coderanch.com/t/133048/Security/programmatically-create-keystore-import-certificate
//        try {
//            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
//
//            char[] password = "some password".toCharArray();
//            ks.load(null, password);
//
//// Store away the keystore.
//            FileOutputStream fos = new FileOutputStream("algo.jks");
//            ks.store(fos, password);
//            fos.close();
//        } catch (KeyStoreException | IOException | NoSuchAlgorithmException | CertificateException ex) {
//            ex.printStackTrace();
//        }
    }

}
