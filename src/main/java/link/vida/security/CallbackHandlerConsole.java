/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.TextOutputCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import link.vida.utils.Utils;

/**
 *
 * @author dcaro
 */
public class CallbackHandlerConsole implements CallbackHandler {

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
            if (callbacks[i] instanceof TextOutputCallback) {

                // display the message according to the specified type
                TextOutputCallback toc = (TextOutputCallback) callbacks[i];
                switch (toc.getMessageType()) {
                    case TextOutputCallback.INFORMATION:
                        System.out.println(toc.getMessage());
                        break;
                    case TextOutputCallback.ERROR:
                        System.out.println("ERROR: " + toc.getMessage());
                        break;
                    case TextOutputCallback.WARNING:
                        System.out.println("WARNING: " + toc.getMessage());
                        break;
                    default:
                        throw new IOException("Unsupported message type: "
                                + toc.getMessageType());
                }

            } else if (callbacks[i] instanceof NameCallback) {

                // prompt the user for a username
                NameCallback nc = (NameCallback) callbacks[i];

                System.err.print(nc.getPrompt());
                System.err.flush();
                nc.setName((new BufferedReader(new InputStreamReader(System.in))).readLine());

            } else if (callbacks[i] instanceof PasswordCallback) {

                // prompt the user for sensitive information
                PasswordCallback pc = (PasswordCallback) callbacks[i];
                System.err.print(pc.getPrompt());
                System.err.flush();
                pc.setPassword(readPassword(System.in));

            } else {
                throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");
            }
        }
    }

    // Reads user password from given input stream.
    private char[] readPassword(InputStream in) throws IOException {

        char[] lineBuffer;
        char[] buf;
        int i;

        buf = lineBuffer = new char[128];

        int room = buf.length;
        int offset = 0;
        int c;

        loop:
        while (true) {
            switch (c = in.read()) {
                case -1:
                case '\n':
                    break loop;

                case '\r':
                    int c2 = in.read();
                    if ((c2 != '\n') && (c2 != -1)) {
                        if (!(in instanceof PushbackInputStream)) {
                            in = new PushbackInputStream(in);
                        }
                        ((PushbackInputStream) in).unread(c2);
                    } else {
                        break loop;
                    }

                default:
                    if (--room < 0) {
                        buf = new char[offset + 128];
                        room = buf.length - offset - 1;
                        System.arraycopy(lineBuffer, 0, buf, 0, offset);
                        Arrays.fill(lineBuffer, ' ');
                        lineBuffer = buf;
                    }
                    buf[offset++] = (char) c;
                    break;
            }
        }

        if (offset == 0) {
            return null;
        }

        char[] ret = new char[offset];
        System.arraycopy(buf, 0, ret, 0, offset);
        Arrays.fill(buf, ' ');

        byte[] passBytes = Utils.toBytes(ret);

        String hashedPass = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passBytes);
            hashedPass = new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CallbackHandlerConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

        Arrays.fill(ret, '\u0000'); // clear sensitive data
        Arrays.fill(passBytes, (byte) 0); // clear sensitive data

        return hashedPass.toCharArray();
    }

}
