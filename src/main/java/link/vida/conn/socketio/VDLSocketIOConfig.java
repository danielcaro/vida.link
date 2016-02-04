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
        super.setHostname("vida.link");
        super.setPort(9090);        
        //super.set //enableCors(new EnableCorsAttribute(Properties.Settings.Default.Cors, "", ""))
//        InputStream stream = VDLSocketIOConfig.class.getResourceAsStream("algo.jks");
//        setKeyStore(stream);
    }

}
