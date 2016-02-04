package link.vida.app;

import com.google.inject.AbstractModule;
import link.vida.admin.AdminConnectorModule;
import link.vida.admin.web.jsf.ModuleAdminJSF;
import link.vida.conn.ConnectorModule;
import link.vida.conn.socketio.ModuleSocketIO;
import link.vida.conn.ws.ModuleWS;
import link.vida.conn.zmq.ModuleZMQ;
import link.vida.crash.CrashGuiceSupport;
import link.vida.data.bus.DataBusModule;
import link.vida.db.ConfigDB;
import link.vida.security.SecurityModule;
import link.vida.session.ModulePeerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoaderModule
        extends AbstractModule {

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void configure() {
        this.logger.info("Available Processors:" + Runtime.getRuntime().availableProcessors());
        this.logger.info("Free Memory / Total Memory:" + Runtime.getRuntime().freeMemory() + " / " + Runtime.getRuntime().maxMemory());
        this.logger.info("Loading Modules...");
        

        install(new ConfigDB());

        install(new SecurityModule());
        
        // cambiar nombre a console .
//        install(new CrashGuiceSupport()); //import crash in my app
        

        install(new ModulePeerManager());
        
        install(new DataBusModule());

        // CONECTORES
        install(new ConnectorModule());
//        install(new ModuleWS());
        install(new ModuleZMQ());
        install(new ModuleSocketIO());

        // ADMIN PLUGINS
        install(new AdminConnectorModule());
        install(new ModuleAdminJSF());
        


        bind(ServiceManager.class).to(ServiceManagerImpl.class).asEagerSingleton();

        this.logger.info("Modules Loaded");
    }


}
