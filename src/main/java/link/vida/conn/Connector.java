/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn;

public interface Connector {   

    public String getConnectorName();

    public void start() ;

    public void stop() ;

//    public int getStatus();

//    // TODO: agrear exepción de dipositivio no se encuentra o mensaje no es posible ser enviado    
//    public void sendMsgToSessionId(Serializable conSessionId, MessageM2M msg);
//    
//    // TODO: agrear exepción de dipositivio no se encuentra o mensaje no es posible ser enviado        
//    public void closeConnection(Serializable connSessionId);
    //TODO: agregar datos sobre estado del conector, hora de inicio dispositivos conectados, msg intercambiados, etc etc     
}
