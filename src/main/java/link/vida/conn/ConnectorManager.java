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

    public void startConnServices();

    public void stopConnServices();

    public ConnService getConnServiceById(Short connId);

    public HashMap<Short, ConnService> getConnServices();
    
    public HashMap<ConnService,String> status();

}
