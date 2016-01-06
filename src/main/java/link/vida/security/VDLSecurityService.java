/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security;

import java.security.Policy;
import java.util.ArrayList;
import javax.security.auth.login.Configuration;

/**
 *
 * @author dcaro
 */
public class VDLSecurityService implements SecurityService{

    public VDLSecurityService() {
        		// Se instala la nueva configuracion de los modulos de Login.
		Configuration.setConfiguration(new VDLAuthConfiguration());                
		
		// Se instala la politica de permisos personalizada
		Policy defaultPolicy = Policy.getPolicy();
		ArrayList<Policy> policies = new ArrayList<>();
		if (defaultPolicy != null) {
			policies.add(defaultPolicy);
		}
		policies.add(new VDLPolicy());
		Policy.setPolicy(new VDLCompositePolicy(policies));
		
		// Se instala el SecurityManager
		System.setSecurityManager(new SecurityManager());
    }
    
    
    
}
