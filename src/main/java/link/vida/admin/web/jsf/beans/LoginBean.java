/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf.beans;

import link.vida.admin.web.jsf.GuiceBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import link.vida.security.CallbackHandlerVDL;
import link.vida.security.VDLAuthConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SessionScoped
public class LoginBean extends GuiceBean {

    private static final long serialVersionUID = 7765876811740798583L;
    final Logger logger = LoggerFactory.getLogger(LoginBean.class);
    

    private String username;
    private String password;

    private boolean loggedIn;

    @ManagedProperty(value = "#{navigationBean}")
    private NavigationBean navigationBean;

    /**
     * Login operation.
     *
     * @return
     */
    public String doLogin() {
        // Get every user from our sample database :)
        try {
            LoginContext lc = new LoginContext(VDLAuthConfiguration.APP_NAME,
                    new CallbackHandlerVDL("email", username, password));
            lc.login();
            loggedIn = true;
            return navigationBean.redirectToWelcome();
        } catch (LoginException le) {
            logger.error("Login Error: ", le.getMessage());
            //    
            // Set login ERROR
            FacesMessage msg = new FacesMessage("Datos incorrectos", le.getMessage());
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null, msg);

            // To to login page
            return navigationBean.toLogin();
        }
    }

    /**
     * Logout operation.
     *
     * @return
     */
    public String doLogout() {
        // Set the paremeter indicating that user is logged in to false
        loggedIn = false;

        // Set logout message
        FacesMessage msg = new FacesMessage("Logout success!", "INFO MSG");
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "/index.xhtml";
    }

    // ------------------------------
    // Getters & Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setNavigationBean(NavigationBean navigationBean) {
        this.navigationBean = navigationBean;
    }

}
