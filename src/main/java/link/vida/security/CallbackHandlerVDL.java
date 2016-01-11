/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 *
 * @author dcaro
 */
public class CallbackHandlerVDL implements CallbackHandler {

    private final String token;
    private final String name;
    private final String password;

    public CallbackHandlerVDL(String token ,String name, String password) {
        this.token = token;
        this.name = name;
        this.password = password;
    }

    /**
     * Invoke an array of Callbacks.
     *
     * <p>
     *
     * @param callbacks an array of <code>Callback</code> objects which contain
     * the information requested by an underlying security service to be
     * retrieved or displayed.
     *
     * @exception java.io.IOException if an input or output error occurs.
     * <p>
     *
     * @exception UnsupportedCallbackException if the implementation of this
     * method does not support one or more of the Callbacks specified in the
     * <code>callbacks</code> parameter.
     */
    @Override
    public void handle(Callback[] callbacks)
            throws IOException, UnsupportedCallbackException {

        for (int i = 0; i < callbacks.length; i++) {
            if (callbacks[i] instanceof TokenCallback) {
                TokenCallback tc = (TokenCallback) callbacks[i];
                tc.setToken(token);
            }else if (callbacks[i] instanceof NameCallback) {
                NameCallback nc = (NameCallback) callbacks[i];
                nc.setName(name);
            } else if (callbacks[i] instanceof PasswordCallback) {
                PasswordCallback pc = (PasswordCallback) callbacks[i];
                pc.setPassword(password.toCharArray());
            } else {
                throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
            }
        }
    }

}
