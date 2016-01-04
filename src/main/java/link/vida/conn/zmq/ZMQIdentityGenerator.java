/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.zmq;

import com.google.inject.Inject;
import com.spotify.netty4.handler.codec.zmtp.ZMTPIdentityGenerator;
import com.spotify.netty4.handler.codec.zmtp.ZMTPSession;
import java.nio.ByteBuffer;
import link.vida.session.VDLIdentityGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class ZMQIdentityGenerator implements ZMTPIdentityGenerator {

    @Inject
    VDLIdentityGenerator vDLIdentityGenerator;
    private static final Logger log = LoggerFactory.getLogger(ZMQIdentityGenerator.class);

    /**
     * An identity generator that keeps an integer counter per
     * {@link ZMTPSocket}.
     */
    @Override
    public ByteBuffer generateIdentity(ZMTPSession session) {
        // Este generador de identificador debe llamar al identificador 
        // VDLIdentityGenerator 
        final ByteBuffer generated = ByteBuffer.allocate(5);
        //generated.put((byte) 0);
        Integer id = vDLIdentityGenerator.generateIdentity();
        log.info("GENERANDO IDENT: " + id);
        generated.putInt(id);
        generated.flip();
        return generated;
    }

}
