/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.data.bus;

import link.vida.msgs.VDLAck;
import link.vida.msgs.VDLMsgData;

/**
 *
 * @author dcaro
 */
public interface InboxAPI {

    interface MsgInboxCallback {
        void onMsgProcesed(VDLAck ack);
    }
    
    void msgtReceived(VDLMsgData inDLMsgData, MsgInboxCallback callback);

}
