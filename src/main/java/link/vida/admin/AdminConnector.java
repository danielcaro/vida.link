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
public interface AdminConnector {

    public String getConnectorName();
    

    public void start();

    public void stop();

}
