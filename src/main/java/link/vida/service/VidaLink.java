/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.service;

/**
 *
 * @author dcaro
 */
public class VidaLink {

    /**
     * @param args the command line arguments
     */
   

    public static void main(String[] args) {
        
        (new Thread(new RunneableBroker())).start();
        
        (new Thread(new RunneableWebConsole())).start();
        
        (new Thread(new RunneableWebService())).start();
        
        (new Thread(new RunneableJSF())).start();
                      
    }
      
    
}
