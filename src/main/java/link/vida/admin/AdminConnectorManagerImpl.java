/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin;

import com.google.inject.Inject;
import com.google.inject.Injector;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: 
 * - Add functions to start and stop each service
 * - If connector is enable on Database, enable it.
 * - Improve connector status.
 * - getConnector By ConnName
 * @author daniel
 */
public class AdminConnectorManagerImpl implements AdminConnectorManager {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HashMap<Integer, AdminConnector> connServices = null;
    
    @Inject
    private Injector injector;
    
    @Inject
    Set<AdminConnector> adminConnectors;

    

    private void attachConnServices() {
        connServices = new HashMap<>();
        int id = 0;
        for (AdminConnector connector : adminConnectors) {
            logger.info("ADDED " + connector.getConnectorName());
            connServices.put(id, connector);
            id = id + 1;
        }
        
    }
    
    
    
    @Override
    public AdminConnector getAdminConnServiceById(Integer connId){
        return getAdminConnServices().get(connId);
    }

    @Override
    public HashMap<Integer, AdminConnector> getAdminConnServices() {
        if (connServices == null) {
            attachConnServices();
        }
        return connServices;
    }       

    @Override
    public void startAdminConnServices() {
        logger.info("Iniciando Conectores");
        Iterator<Integer> connNames = getAdminConnServices().keySet().iterator();
        while (connNames.hasNext()) {
            // - revisar si está en la base de datos.        
            // - sino está se agrega en modo desactivado
            // - si es está en DB y es activo proceder a levantar connector            
            AdminConnector conn = getAdminConnServices().get(connNames.next());
            logger.info("STARTING ADMIN CONNECTOR (" + conn.getConnectorName() + ")");                
            conn.start();            
            logger.info("ADMIN CONNECTOR STARTED (" + conn.getConnectorName() + ")"); 
        }
    }

    @Override
    public void stopAdminConnServices() {
        logger.info("Cerrando Conectores");
        Iterator<Integer> connNames = getAdminConnServices().keySet().iterator();
        while (connNames.hasNext()) {
            AdminConnector conn = getAdminConnServices().get(connNames.next());
            logger.info("STOPING CONNECTOR (" + conn.getConnectorName() + ")");    
            conn.stop();
        }
    }
}
