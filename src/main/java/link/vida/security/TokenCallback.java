/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security;

import javax.security.auth.callback.Callback;

/**
 *
 * @author dcaro
 */
public class TokenCallback implements Callback, java.io.Serializable { 
    
    
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
          
    
}
