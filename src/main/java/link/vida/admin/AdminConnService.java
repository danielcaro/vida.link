/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin;

/**
 *
 * @author dcaro
 */
public interface AdminConnService {

    public String getAdminConnName();
    
    public Integer getAdminConnectorId();

    public void setAdminConnectorId(Integer connectorId);    

    public void start();

    public void stop();

}
