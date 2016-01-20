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
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: - Add functions to start and stop each service - If connector is enable
 * on Database, enable it. - Improve connector status. - getConnector By
 * ConnName
 *
 *
 * UNINSTALLED INSTALLED RESOLVED STARTING STOPPING ACTIVE
 *
 * - Se buscan
 *
 *
 *
 * @author daniel
 */
public class ConnectorManagerImpl implements ConnectorManager {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private HashMap<Integer, Connector> connServices = null;
    private Injector injector;

    @Inject
    Set<Connector> connectors;

    @Inject
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    private void attachConnServices() {
        connServices = new HashMap<>();
        int id = 0;
        for (Connector connector : connectors) {
            logger.info("ADDED " + connector.getConnectorName());
            connServices.put(id, connector);
            id = id + 1;
        }

    }


    @Override
    public Connector getConnectorById(Integer connId) {
        return getConnectors().get(connId);
    }

    @Override
    public HashMap<Integer, Connector> getConnectors() {
        if (connServices == null) { attachConnServices();}
        return connServices;
    }

    @Override
    public void startConnectors() {
        logger.info("Iniciando Conectores");
        Iterator<Integer> connNames = getConnectors().keySet().iterator();
        while (connNames.hasNext()) {
            // - revisar si está en la base de datos.        
            // - sino está se agrega en modo desactivado
            // - si es está en DB y es activo proceder a levantar connector            
            Connector conn = getConnectors().get(connNames.next());
            logger.info("STARTING CONNECTOR (" + conn.getConnectorName() + ")");
            conn.start();
            logger.info("CONNECTOR STARTED (" + conn.getConnectorName() + ")");
        }
    }

    @Override
    public void stopConnectors() {
        logger.info("Cerrando Conectores");
        Iterator<Integer> connNames = getConnectors().keySet().iterator();
        while (connNames.hasNext()) {
            Connector conn = getConnectors().get(connNames.next());
            logger.info("STOPING CONNECTOR (" + conn.getConnectorName() + ")");
            conn.stop();
        }
    }

}
