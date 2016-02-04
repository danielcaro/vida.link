/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.socketio;

import com.corundumstudio.socketio.Configuration;
import link.vida.utils.FileConfig;

/**
 *
 * @author dcaro
 */
public class VDLSocketIOConfig extends Configuration {

    public VDLSocketIOConfig() {
        //TODO: Manejar excepciones
        // HostName, podría ser ingresado por consola mejor
        // FileConfig.getConfig("hostname", "config.properties")
        super.setHostname("0.0.0.0");
        super.setPort(9090); 
//        InputStream stream = VDLSocketIOConfig.class.getResourceAsStream("algo.jks");
//        setKeyStore(stream);
    }

}
