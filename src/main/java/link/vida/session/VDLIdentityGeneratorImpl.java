/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Necesitamos mejorar las identificaciones de dispositivo, de las identificaciones 
 * en las sessiones , el mensaje se envía a un dispositivo, el cual puede tener varias 
 * sessiones abiertas, 
 * 
 * Las sessiones no cuenta con id  de dispositvio hasta que el login haya sido exitoso.
 * Las sessiones sin login, se cierra después de  5 segundos.
 * 
 * Web Service
 * - El id se asigna si el login es exitoso.
 * - 
 * 
 * ZMQ
 * - relación id ZMQ y id sessión VDL
 * - 
 * 
 * 
 *
 * @author dcaro
 */
public class VDLIdentityGeneratorImpl implements VDLIdentityGenerator {

    private static final Logger log = LoggerFactory.getLogger(VDLIdentityGeneratorImpl.class);
    private final AtomicInteger peerIdCounter = new AtomicInteger(new SecureRandom().nextInt());

    @Override
    public Integer generateIdentity() {
        // a que se le genera un id, y cómo se accede a este posteriormente.
        return peerIdCounter.incrementAndGet();
    }

}
