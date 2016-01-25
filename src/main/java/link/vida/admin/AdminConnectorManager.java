/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin;

import java.util.HashMap;

/**
 *
 * @author daniel
 */
public interface AdminConnectorManager {

    public void startAdminConnServices();

    public void stopAdminConnServices();

    public AdminConnector getAdminConnServiceById(Integer connId);

    public HashMap<Integer, AdminConnector> getAdminConnServices();

}
