/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.data.bus;

import link.vida.msgs.VDLAck;
import link.vida.msgs.VDLMsgData;
import rx.Observable;

/**
 *
 * @author dcaro
 */
public interface VDLInbox {
    
    public Observable<VDLAck> rcvMsg(final VDLMsgData inComeMsg);
}
