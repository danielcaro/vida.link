/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.wicket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import link.vida.service.Peer;
import link.vida.service.PeerInfo;
import link.vida.service.VDLChInit;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;

/**
 *
 * @author dcaro
 */
public class PeersProvider extends SortableDataProvider {
    
    private List<PeerInfo> newList ;

    /**
     *
     * @param first
     * @param count
     * @return
     */
    @Override
    public Iterator<PeerInfo> iterator(final long first, final long count) {
        
        newList = new ArrayList<>();
       
        Iterator<Peer> peersIterator = VDLChInit.getPeersManager().peers().iterator();
        while (peersIterator.hasNext()) {
            PeerInfo peerInf =  new PeerInfo();
            peerInf.setId("" + peersIterator.next().getPeerId());
            newList.add(peerInf);
        }

        // Return the data for the current page - this can be determined only after sorting
        return newList.iterator();
    }

    @Override
    public long size() {
        return VDLChInit.getPeersManager().peers().size();
    }

    @Override
    public IModel<PeerInfo> model(final Object obj) {
        return new AbstractReadOnlyModel<PeerInfo>() {
            @Override
            public PeerInfo getObject() {
                return (PeerInfo) obj;
            }
        };
    }

}