/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.ws;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import link.vida.service.Peer;
import link.vida.service.PeerInfo;
import link.vida.service.VDLChInit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/vdl")
public class WebServiceVDL {

    private static final Logger log = LoggerFactory.getLogger(WebServiceVDL.class);

    @GET
    @Path("list")  
    @Produces(MediaType.APPLICATION_JSON)
    public List<PeerInfo> list() {

        List<PeerInfo> newList = new ArrayList<>();
        Iterator<Peer> peersIterator = VDLChInit.getPeersManager().peers().iterator();
        while (peersIterator.hasNext()) {
            PeerInfo peerInf = new PeerInfo();
            peerInf.setId("" + peersIterator.next().getPeerId());
            newList.add(peerInf);
        }
        log.info("RETURN:" + newList.size());
        return newList;
    }
}
