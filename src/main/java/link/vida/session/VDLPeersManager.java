/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.session;

import java.util.List;

/**
 *
 * @author dcaro
 */
public interface VDLPeersManager {

    public void register(final Integer identity, final VDLPeer peer);

    public void deregister(final Integer identity);

    public List<VDLPeer> peers();

}
