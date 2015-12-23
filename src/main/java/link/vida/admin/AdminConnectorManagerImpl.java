/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin;

import link.vida.conn.*;
import com.google.inject.Binding;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
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
    private HashMap<Short, AdminConnService> connServices = null;
    private Injector injector;
    
//    @Inject(optional=true)
//    private ConnectorDao connectorDao;

    @Inject
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    private void attachConnServices() {
        connServices = new HashMap<Short, AdminConnService>();
        int id = 0;
        for (Map.Entry<Key<?>, Binding<?>> entry : injector.getBindings().entrySet()) {
            if (entry.getKey().getTypeLiteral().getType().toString().contains("interface")) {
                String[] inter = entry.getKey().getTypeLiteral().getType().toString().split(" ");
                if (inter[1].equals(AdminConnService.class.getCanonicalName())) {
                    
                    AdminConnService conn = (AdminConnService) injector.getInstance(entry.getKey());
                    //TODO: Avisar que el connector está activo
                    conn.setAdminConnectorId(id);
                    logger.info("PUT[" + conn.getAdminConnectorId() + "]" + conn.getAdminConnName());
                    connServices.put(conn.getAdminConnectorId().shortValue(), conn);
                    id = id + 1;
                }
            }
        }
    }
    
    
    
    @Override
    public AdminConnService getAdminConnServiceById(Short connId){
        return getAdminConnServices().get(connId);
    }

    @Override
    public HashMap<Short, AdminConnService> getAdminConnServices() {
        if (connServices == null) {
            attachConnServices();
        }
        return connServices;
    }       

    @Override
    public void startAdminConnServices() {
        logger.info("Iniciando Conectores");
        Iterator<Short> connNames = getAdminConnServices().keySet().iterator();
        while (connNames.hasNext()) {
            // - revisar si está en la base de datos.        
            // - sino está se agrega en modo desactivado
            // - si es está en DB y es activo proceder a levantar connector            
            AdminConnService conn = getAdminConnServices().get(connNames.next());
            logger.info("STARTING ADMIN CONNECTOR (" + conn.getAdminConnName() + ")");                
            conn.start();            
            logger.info("ADMIN CONNECTOR STARTED (" + conn.getAdminConnName() + ")"); 
        }
    }

    @Override
    public void stopAdminConnServices() {
        logger.info("Cerrando Conectores");
        Iterator<Short> connNames = getAdminConnServices().keySet().iterator();
        while (connNames.hasNext()) {
            AdminConnService conn = getAdminConnServices().get(connNames.next());
            logger.info("STOPING CONNECTOR (" + conn.getAdminConnName() + ")");    
            conn.stop();
        }
    }
}
