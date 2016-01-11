/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf.beans;

import link.vida.admin.web.jsf.GuiceBean;
import java.io.Serializable;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
 
/**
 * Simple navigation bean
 * @author itcuties
 *
 */
@ManagedBean
@SessionScoped
public class NavigationBean extends GuiceBean{
 
    private static final long serialVersionUID = 1520318172495977648L;
 
    /**
     * Redirect to login page.
     * @return Login page name.
     */
    public String redirectToLogin() {
        return "/login.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toLogin() {
        return "/login.xhtml";
    }
    
    
         
    /**
     * Go to login page.
     * @return Login page name.
     */
    public String toIndex() {
        return "/index.xhtml";
    }
     
    /**
     * Redirect to info page.
     * @return Info page name.
     */
    public String redirectToInfo() {
        return "/info.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to info page.
     * @return Info page name.
     */
    public String toInfo() {
        return "/info.xhtml";
    }
     
    /**
     * Redirect to welcome page.
     * @return Welcome page name.
     */
    public String redirectToWelcome() {
        return "/admin/index.xhtml?faces-redirect=true";
    }
     
    /**
     * Go to welcome page.
     * @return Welcome page name.
     */
    public String toWelcome() {
        return "/admin/index.xhtml";
    }
     
}
