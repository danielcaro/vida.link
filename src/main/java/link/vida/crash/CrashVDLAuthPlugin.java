/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.crash;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import link.vida.admin.web.jsf.beans.LoginBean;
import link.vida.security.CallbackHandlerVDL;
import link.vida.security.VDLAuthConfiguration;
import org.crsh.auth.AuthenticationPlugin;
import org.crsh.plugin.CRaSHPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 * https://github.com/linagora/crash-guice
 */
public class CrashVDLAuthPlugin extends
        CRaSHPlugin<AuthenticationPlugin> implements
        AuthenticationPlugin<Object> {

    
    final Logger logger = LoggerFactory.getLogger(LoginBean.class);

    @Override
    public AuthenticationPlugin getImplementation() {
        return this;
    }

    @Override
    public String getName() {
        return "vida.link.crash";
    }

    @Override
    public Class getCredentialType() {
        return String.class;
    }

    @Override
    public boolean authenticate(String username, Object password) throws Exception {
        try {
            LoginContext lc = new LoginContext(VDLAuthConfiguration.APP_NAME,
                    new CallbackHandlerVDL("login", username, (String) password));
            lc.login();
            return true;
        } catch (LoginException le) {
            logger.error("Login Error: ", le.getMessage());
            return false;
        }
    }

}
