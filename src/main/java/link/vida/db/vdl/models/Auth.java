/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db.vdl.models;

/**
 *
 * @author dcaro
 */
public class Auth {

    private Integer peerId;
    private String authToken;
    private String authId;
    private String authKey;
    private boolean enable;

    public boolean getEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
          

    public Integer getPeerId() {
        return peerId;
    }

    public void setPeerId(Integer peerId) {
        this.peerId = peerId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    @Override
    public String toString() {
        return "Auth{" + "peerId=" + peerId + ", authToken=" + authToken + ", authId=" + authId + ", authKey=" + authKey + '}';
    }


    
}
