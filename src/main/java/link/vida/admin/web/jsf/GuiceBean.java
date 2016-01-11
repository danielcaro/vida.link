/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import link.vida.app.VidaLink;

/**
 *
 * @author dcaro
 */
public class GuiceBean implements Serializable  {

    /*
    Opción para que se inicie la injección de guice desde JSF
     */
    @PostConstruct
    public void init() {
        VidaLink.injector.injectMembers(this);
    }
}
