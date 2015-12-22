package link.vida.app;

import com.google.inject.AbstractModule;
import link.vida.conn.zmq.RunneableBroker;
import link.vida.admin.web.jsf.RunneableWebConsole;
import link.vida.conn.ws.RunneableWebService;
import link.vida.db.ConfigDB;
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


//        bind(RunneableBroker.class).to(RunneableBroker.class).asEagerSingleton();
//        bind(RunneableWebService.class).to(RunneableWebService.class).asEagerSingleton();
//        bind(RunneableWebConsole.class).to(RunneableWebConsole.class).asEagerSingleton();

        this.logger.info("Modules Loaded");
    }
}
