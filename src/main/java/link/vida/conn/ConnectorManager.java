/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn;

import java.util.HashMap;

/**
 *
 * @author daniel
 */
public interface ConnectorManager {

    public void startConnectors();

    public void stopConnectors();

    public Connector getConnectorById(Integer connId);    

    public HashMap<Integer, Connector> getConnectors();
    

}
