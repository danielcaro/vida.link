/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.app;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import link.vida.admin.web.jsf.RunneableWebConsole;
import link.vida.conn.ws.RunneableWebService;
import link.vida.conn.zmq.RunneableBroker;

/**
 *
 * @author dcaro
 */
@Singleton
public class ServiceManager {

    @Inject
    RunneableBroker runneableBroker;

    @Inject
    RunneableWebConsole runneableWebConsole;

    @Inject
    RunneableWebService runneableWebService;

    public void start() {

        if (!runneableBroker.isAlive()) {
            runneableBroker.start();
        }

        if (!runneableWebConsole.isAlive()) {
            runneableWebConsole.start();
        }

        if (!runneableWebService.isAlive()) {
            runneableWebService.start();
        }

    }

}
