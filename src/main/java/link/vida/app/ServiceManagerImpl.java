/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.app;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import link.vida.admin.AdminConnectorManager;
import link.vida.conn.ConnectorManager;

/**
 *
 * @author dcaro
 */
@Singleton
public class ServiceManagerImpl implements ServiceManager {

    @Inject(optional = true)
    private ConnectorManager connectorManager;

    @Inject(optional = true)
    private AdminConnectorManager adminConnectorManager;

    @Override
    public void start() {
        connectorManager.startConnServices();
        adminConnectorManager.startAdminConnServices();
    }

}
