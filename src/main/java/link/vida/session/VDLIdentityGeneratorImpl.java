/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicInteger;
import link.vida.conn.zmq.ZMQChInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class VDLIdentityGeneratorImpl implements VDLIdentityGenerator {

    private static final Logger log = LoggerFactory.getLogger(VDLIdentityGeneratorImpl.class);
    private final AtomicInteger peerIdCounter = new AtomicInteger(new SecureRandom().nextInt());

    @Override
    public Integer generateIdentity() {
        return peerIdCounter.incrementAndGet();
    }

}
