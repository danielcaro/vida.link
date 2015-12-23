/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn;

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
public class ConnectorManagerImpl implements ConnectorManager {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HashMap<Short, ConnService> connServices = null;
    private Injector injector;
    
//    @Inject(optional=true)
//    private ConnectorDao connectorDao;

    @Inject
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    private void attachConnServices() {
        connServices = new HashMap<Short, ConnService>();
        int id = 0;
        for (Map.Entry<Key<?>, Binding<?>> entry : injector.getBindings().entrySet()) {
            if (entry.getKey().getTypeLiteral().getType().toString().contains("interface")) {
                String[] inter = entry.getKey().getTypeLiteral().getType().toString().split(" ");
                if (inter[1].equals(ConnService.class.getCanonicalName())) {
                    
                    ConnService conn = (ConnService) injector.getInstance(entry.getKey());
                    //TODO: Avisar que el connector está activo
                    conn.setConnectorId(id);
                    logger.info("PUT[" + conn.getConnectorId() + "]" + conn.getConnName());
                    connServices.put(conn.getConnectorId().shortValue(), conn);
                    id = id + 1;
                }
            }
        }
    }
    
    
    
    @Override
    public ConnService getConnServiceById(Short connId){
        return getConnServices().get(connId);
    }

    @Override
    public HashMap<Short, ConnService> getConnServices() {
        if (connServices == null) {
            attachConnServices();
        }
        return connServices;
    }       

    @Override
    public void startConnServices() {
        logger.info("Iniciando Conectores");
        Iterator<Short> connNames = getConnServices().keySet().iterator();
        while (connNames.hasNext()) {
            // - revisar si está en la base de datos.        
            // - sino está se agrega en modo desactivado
            // - si es está en DB y es activo proceder a levantar connector            
            ConnService conn = getConnServices().get(connNames.next());
            logger.info("STARTING CONNECTOR (" + conn.getConnName() + ")");                
            conn.start();            
            logger.info("CONNECTOR STARTED (" + conn.getConnName() + ")"); 
        }
    }

    @Override
    public void stopConnServices() {
        logger.info("Cerrando Conectores");
        Iterator<Short> connNames = getConnServices().keySet().iterator();
        while (connNames.hasNext()) {
            ConnService conn = getConnServices().get(connNames.next());
            logger.info("STOPING CONNECTOR (" + conn.getConnName() + ")");    
            conn.stop();
        }
    }
}
