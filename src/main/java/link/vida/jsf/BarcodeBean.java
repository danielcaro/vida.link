/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.jsf;



import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class BarcodeBean {

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
        return  protocol + "www." + url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    
    


}
