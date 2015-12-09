/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.webconsole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import link.vida.service.Peer;
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

    class PeerInfo implements Serializable {

        private final String id;

        public PeerInfo(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

    }

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
            newList.add(new PeerInfo("" + peersIterator.next().session().peerIdentity().getInt(0)));
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
