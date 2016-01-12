/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security;

import com.google.inject.AbstractModule;

/**
 * JAAS
 * http://docs.oracle.com/javase/8/docs/technotes/guides/security/jaas/JAASRefGuide.html#CallbackHandler
 * http://docs.oracle.com/javase/8/docs/api/javax/security/auth/callback/CallbackHandler.html
 * http://docs.oracle.com/javase/8/docs/api/javax/security/auth/login/LoginContext.html
 * http://docs.oracle.com/javase/8/docs/technotes/guides/security/jaas/tutorials/GeneralAcnOnly.html#SampleAcn
 * http://docs.oracle.com/javase/8/docs/technotes/guides/security/jaas/JAASLMDevGuide.html
 * 
 * 
 * http://www.journaldev.com/7252/jsf-authentication-login-logout-database-example
 * https://www.owasp.org/index.php/Hashing_Java
 * http://www.mindrot.org/projects/jBCrypt/
 * 
 * Tipos de Autentificación: 
 *  - Login: email- password
 *  - MAC: mac address y key
 *  - UUID : uuid y key
 * 
 * Tipos de Agrupaciones
 *  - Tenant / namespace: Cliente o inquilino del sistema
 *  - Unit : Unidad oganizacional de la empresa
 *  - App : Aplicación y/o servicio 
 * 
 * 
 * Los permisos se deben configurar por parte de la aplicación, 
 * existe un webservices para la configuración de los privilegios 
 * de los dispositivos, y el usuario y clave administrador/manager, es el que 
 * puede determinar los permiso del resto de dispositivos.
 * 
 * Cáda administrador, puede crear roles y asignarle nombres que el determine.
 *  - 
 * 
 * 
 * 
 * 
 * @author dcaro
 */
public class SecurityModule  extends AbstractModule {

    @Override
    protected void configure() {
         bind(SecurityService.class).to(VDLSecurityService.class).asEagerSingleton();
        
    }
    
    
    
}
