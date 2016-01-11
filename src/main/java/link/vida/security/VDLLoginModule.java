/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security;

import com.google.inject.Inject;
import java.util.Map;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.security.auth.callback.*;
import javax.security.auth.login.*;
import link.vida.app.VidaLink;
import link.vida.db.vdl.VdlDao;
import link.vida.db.vdl.models.Auth;
import link.vida.utils.Utils;
import org.mindrot.jbcrypt.BCrypt;

/**
 * <p>
 * This sample LoginModule authenticates users with a password.
 *
 * <p>
 * This LoginModule only recognizes one user: testUser
 * <p>
 * testUser's password is: testPassword
 *
 * <p>
 * If testUser successfully authenticates itself, a <code>SamplePrincipal</code>
 * with the testUser's user name is added to the Subject.
 *
 * <p>
 * This LoginModule recognizes the debug option. If set to true in the login
 * Configuration, debug messages will be output to the output stream,
 * System.out.
 *
 */
public class VDLLoginModule implements LoginModule {

    // initial state
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

    // configurable option
    private boolean debug = false;

    // the authentication status
    private boolean succeeded = false;
    private boolean commitSucceeded = false;

    // username and password
    private String token;
    private String username;
    private char[] password;

    // testUser's SamplePrincipal
    private VDLPrincipal userPrincipal;

    @Inject
    VdlDao vdlDao;

    /**
     * Initialize this <code>LoginModule</code>.
     *
     * <p>
     *
     * @param subject the <code>Subject</code> to be authenticated.
     * <p>
     *
     * @param callbackHandler a <code>CallbackHandler</code> for communicating
     * with the end user (prompting for user names and passwords, for example).
     * <p>
     *
     * @param sharedState shared <code>LoginModule</code> state.
     * <p>
     *
     * @param options options specified in the login <code>Configuration</code>
     * for this particular <code>LoginModule</code>.
     */
    @Override
    public void initialize(Subject subject,
            CallbackHandler callbackHandler,
            Map<java.lang.String, ?> sharedState,
            Map<java.lang.String, ?> options) {

        VidaLink.injector.injectMembers(this);

        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

        // initialize any configured options
        debug = "true".equalsIgnoreCase((String) options.get("debug"));
    }

    /**
     * Authenticate the user by prompting for a user name and password.
     *
     * <p>
     *
     * @return true in all cases since this <code>LoginModule</code> should not
     * be ignored.
     *
     * @exception FailedLoginException if the authentication fails.
     * <p>
     *
     * @exception LoginException if this <code>LoginModule</code> is unable to
     * perform the authentication.
     */
    @Override
    public boolean login() throws LoginException {

//        https://wiki.eclipse.org/Jetty/Tutorial/JAAS
        // prompt for a user name and password
        if (callbackHandler == null) {
            throw new LoginException("Error: no CallbackHandler available "
                    + "to garner authentication information from the user");
        }

        // Para ingreso por consola ...
        // ingreso web, o otros protocolos.
        Callback[] callbacks = new Callback[3];
        callbacks[0] = new TokenCallback();
        callbacks[1] = new NameCallback("id: ");
        callbacks[2] = new PasswordCallback("password: ", false);

        try {
            callbackHandler.handle(callbacks);
            token = ((TokenCallback) callbacks[0]).getToken();
            username = ((NameCallback) callbacks[1]).getName();

            char[] tmpPassword = ((PasswordCallback) callbacks[2]).getPassword();
            if (tmpPassword == null) {
                // treat a NULL password as an empty password
                tmpPassword = new char[0];
            }
            password = new char[tmpPassword.length];
            System.arraycopy(tmpPassword, 0,
                    password, 0, tmpPassword.length);
            ((PasswordCallback) callbacks[2]).clearPassword();

        } catch (java.io.IOException ioe) {
            throw new LoginException(ioe.toString());
        } catch (UnsupportedCallbackException uce) {
            throw new LoginException("Error: " + uce.getCallback().toString()
                    + " not available to garner authentication information "
                    + "from the user");
        }

        // print debugging information
        if (debug) {
            System.out.println("\t\t[VDLLoginModule] "
                    + "user entered token: "
                    + token);
            System.out.println("\t\t[VDLLoginModule] "
                    + "user entered user name: "
                    + username);
            System.out.print("\t\t[VDLLoginModule] "
                    + "user entered password: ");
            for (int i = 0; i < password.length; i++) {
                System.out.print(password[i]);
            }
            System.out.println();
        }

        // verify the username/password
        boolean usernameCorrect = false;

        // revisar si usuario existe, 
        // luego revisar si la clave para dicho usuario coinciden.
        Auth auth = vdlDao.findAuth(token, username);
        System.out.println("\t\t[auth] =  " + auth);

        if (auth != null) {
            usernameCorrect = true;
        }

        // mejorar autentificación
        if (usernameCorrect && BCrypt.checkpw(String.copyValueOf(password), auth.getAuthKey())) {
            // authentication succeeded!!!
            if (debug) {
                System.out.println("\t\t[VDLLoginModule] "
                        + "authentication succeeded");
            }
            succeeded = true;
            return true;
        } else {

            // authentication failed -- clean out state
            if (debug) {
                System.out.println("\t\t[VDLLoginModule] "
                        + "authentication failed");
            }
            succeeded = false;
            username = null;
            for (int i = 0; i < password.length; i++) {
                password[i] = ' ';
            }
            password = null;
            if (!usernameCorrect) {
                throw new FailedLoginException("User Name Incorrect");
            } else {
                throw new FailedLoginException("Password Incorrect");
            }
        }
    }

    /**
     * <p>
     * This method is called if the LoginContext's overall authentication
     * succeeded (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL
     * LoginModules succeeded).
     *
     * <p>
     * If this LoginModule's own authentication attempt succeeded (checked by
     * retrieving the private state saved by the <code>login</code> method),
     * then this method associates a <code>SamplePrincipal</code> with the
     * <code>Subject</code> located in the <code>LoginModule</code>. If this
     * LoginModule's own authentication attempted failed, then this method
     * removes any state that was originally saved.
     *
     * <p>
     *
     * @exception LoginException if the commit fails.
     *
     * @return true if this LoginModule's own login and commit attempts
     * succeeded, or false otherwise.
     */
    @Override
    public boolean commit() throws LoginException {
        if (succeeded == false) {
            return false;
        } else {
            // add a Principal (authenticated identity)
            // to the Subject

            // assume the user we authenticated is the SamplePrincipal
            userPrincipal = new VDLPrincipal(username);
            if (!subject.getPrincipals().contains(userPrincipal)) {
                subject.getPrincipals().add(userPrincipal);
            }

            if (debug) {
                System.out.println("\t\t[SampleLoginModule] "
                        + "added SamplePrincipal to Subject");
            }

            // in any case, clean out state
            username = null;
            for (int i = 0; i < password.length; i++) {
                password[i] = ' ';
            }
            password = null;

            commitSucceeded = true;
            return true;
        }
    }

    /**
     * <p>
     * This method is called if the LoginContext's overall authentication
     * failed. (the relevant REQUIRED, REQUISITE, SUFFICIENT and OPTIONAL
     * LoginModules did not succeed).
     *
     * <p>
     * If this LoginModule's own authentication attempt succeeded (checked by
     * retrieving the private state saved by the <code>login</code> and
     * <code>commit</code> methods), then this method cleans up any state that
     * was originally saved.
     *
     * <p>
     *
     * @exception LoginException if the abort fails.
     *
     * @return false if this LoginModule's own login and/or commit attempts
     * failed, and true otherwise.
     */
    @Override
    public boolean abort() throws LoginException {
        if (succeeded == false) {
            return false;
        } else if (succeeded == true && commitSucceeded == false) {
            // login succeeded but overall authentication failed
            succeeded = false;
            username = null;
            if (password != null) {
                for (int i = 0; i < password.length; i++) {
                    password[i] = ' ';
                }
                password = null;
            }
            userPrincipal = null;
        } else {
            // overall authentication succeeded and commit succeeded,
            // but someone else's commit failed
            logout();
        }
        return true;
    }

    /**
     * Logout the user.
     *
     * <p>
     * This method removes the <code>SamplePrincipal</code> that was added by
     * the <code>commit</code> method.
     *
     * <p>
     *
     * @exception LoginException if the logout fails.
     *
     * @return true in all cases since this <code>LoginModule</code> should not
     * be ignored.
     */
    @Override
    public boolean logout() throws LoginException {

        subject.getPrincipals().remove(userPrincipal);
        succeeded = false;
        succeeded = commitSucceeded;
        username = null;
        if (password != null) {
            for (int i = 0; i < password.length; i++) {
                password[i] = ' ';
            }
            password = null;
        }
        userPrincipal = null;
        return true;
    }

}
