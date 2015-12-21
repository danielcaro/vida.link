package link.vida.gw;

import com.google.inject.AbstractModule;
import link.vida.service.RunneableBroker;
import link.vida.service.RunneableWebConsole;
import link.vida.service.RunneableWebService;
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

//        (new Thread(new RunneableBroker())).start();
//
////        (new Thread(new RunneableWicket())).start();
//        (new Thread(new RunneableWebService())).start();
//
//        (new Thread(new RunneableJSF())).start();

//z        bind(TunnelSSH.class).to(TunnelSSHImpl.class).asEagerSingleton();
//        bind(ActiveRecord.class).to(ActiveRecordJDBC.class).asEagerSingleton();
//        
//        install(new ConfigJmineopsDB());
//        install(new ConfigTotalviewDB());
//        install(new ConfigMtgDB());
//
//        bind(MTGSync.class).to(MTGSyncImpl.class).asEagerSingleton();
//        bind(RunnerETL.class).to(RunnerETLImpl.class).asEagerSingleton();
//        
        this.logger.info("Modules Loaded");
    }
}
