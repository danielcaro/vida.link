/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio;

import com.corundumstudio.socketio.Configuration;

/**
 *
 * @author dcaro
 */
public class VDLSocketIOConfig extends Configuration {

    public VDLSocketIOConfig() {
        setHostname("localhost");
        setPort(9090);
    }

}
