package link.vida.db.vdl.models;

import java.io.Serializable;

public class AuthKey implements Serializable {

    private String authToken;

    private String authId;

    private static final long serialVersionUID = 1L;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken == null ? null : authToken.trim();
    }

    public String getAuthId() {
        return authId;
    }

    public void setAuthId(String authId) {
        this.authId = authId == null ? null : authId.trim();
    }

    public AuthKey(String authToken, String authId) {
        this.authToken = authToken;
        this.authId = authId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AuthKey other = (AuthKey) that;
        return (this.getAuthToken() == null ? other.getAuthToken() == null : this.getAuthToken().equals(other.getAuthToken()))
                && (this.getAuthId() == null ? other.getAuthId() == null : this.getAuthId().equals(other.getAuthId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAuthToken() == null) ? 0 : getAuthToken().hashCode());
        result = prime * result + ((getAuthId() == null) ? 0 : getAuthId().hashCode());
        return result;
    }

}
