/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package link.vida.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Xein
 */

public class VDLCredentialsMatcher implements CredentialsMatcher  {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        logger.info("doCredentialsMatch");
        logger.info("toke:" + token.getPrincipal() + " " + String.valueOf(token.getCredentials()));
        logger.info("info:" + info.getPrincipals() + " " + info.getCredentials());
        String passIn = (String)token.getCredentials();
        String passSystem = (String)info.getCredentials();
        return passIn.equals(passSystem);
    }
}
