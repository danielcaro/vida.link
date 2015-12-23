/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin;

import link.vida.conn.*;
import java.util.HashMap;

/**
 *
 * @author daniel
 */
public interface AdminConnectorManager {

    public void startAdminConnServices();

    public void stopAdminConnServices();

    public AdminConnService getAdminConnServiceById(Short connId);

    public HashMap<Short, AdminConnService> getAdminConnServices();

}
