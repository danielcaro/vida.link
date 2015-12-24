/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.conn.ws.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import link.vida.conn.zmq.ZMQPeer;
import link.vida.broker.PeerInfo;
import link.vida.conn.zmq.ZMQChInit;
import link.vida.db.vdl.VdlDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/vdl")
@Singleton
public class WebServiceVDL {

    private static final Logger log = LoggerFactory.getLogger(WebServiceVDL.class);

    @Inject
    VdlDao vdlDao;

    @Inject
    ZMQChInit zMQChInit;
    
    @Context 
    HttpServletRequest request;

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    // @Consumes(MediaType.APPLICATION_JSON)
    public List<PeerInfo> list() {

        List<PeerInfo> newList = new ArrayList<>();
        Iterator<ZMQPeer> peersIterator = zMQChInit.getPeersManager().peers().iterator();
        while (peersIterator.hasNext()) {
            PeerInfo peerInf = new PeerInfo();
            peerInf.setId("" + peersIterator.next().getPeerId());
            newList.add(peerInf);
        }
        HttpSession session = request.getSession();
             
        log.info("RETURN:" + newList.size() + " :" + session.getId());
        return newList;
    }

    @GET
    @Path("list_db")
    @Produces(MediaType.APPLICATION_JSON)
    public List<link.vida.db.vdl.models.Peer> listDB() {
        return vdlDao.peersList();
    }
}
