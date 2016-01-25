/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.data.bus;

import java.util.logging.Level;
import java.util.logging.Logger;
import link.vida.msgs.VDLAck;
import link.vida.msgs.VDLMsgData;
import rx.Observable;
import rx.Subscriber;

/**
 *
 * @author dcaro
 */
public class VDLInboxImpl implements VDLInbox {

    InboxAPI api;
    // https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava
    // http://josehernandez.xyz/2015/12/introduccion-a-reactivex/

    @Override
    public Observable<VDLAck> rcvMsg(final VDLMsgData inComeMsg) {
        return Observable.create(subscriber -> {
            try {
                subscriber.onStart();
                Thread.sleep(2000);
                subscriber.onNext(new VDLAck());                
                System.out.println("INCOME MSG" + inComeMsg);                               
                subscriber.onCompleted();
            } catch (InterruptedException ex) {
                subscriber.onError(ex);
            }
        });
    }
}
