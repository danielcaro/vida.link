/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import com.google.inject.Inject;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import link.vida.app.VidaLink;
import link.vida.db.vdl.VdlDao;

@ManagedBean
@SessionScoped
public class BarcodeBean {

    @Inject
    VdlDao vdlDao;

    private String code39 = "1234567890128";
    private String url2 = "http://www.bootsfaces.net";
    private String protocol = "http://";
    private String url = "angularfaces.net";

    public String getCode39() {
        return code39;
    }

    public void setCode39(String code39) {
        this.code39 = code39;
    }

    public String getUrl2() {
        return url2;
    }

    public void setUrl2(String url2) {
        this.url2 = url2;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getCompleteURL() {
        return protocol + "www." + url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /*
    Opción para que se inicie la injección de guice desde JSF
    */
    @PostConstruct
    public void init() {
        VidaLink.injector.injectMembers(this);
    }

}
