/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vidaware.vida.link.msgs;

import com.google.gson.annotations.SerializedName;
import java.util.Date;

/**
 *
 * @author dcaro
 */
public class VDLMsg {


    private final String vdlClass;    
    private final VDLMsgData vdlMsg;

    public VDLMsg(String vdlClass, VDLMsgData vdlMsg) {
        this.vdlClass = vdlClass;        
        this.vdlMsg = vdlMsg;        
    }

    @Override
    public String toString() {
        return "VDLMsg{" + "vdlClass=" + vdlClass + ", vdlMsg=" + vdlMsg + '}';
    }

    private class VDLMsgData{
        private final String data;
        private final Date time;

        public VDLMsgData(String data, Date time) {
            this.data = data;
            this.time = time;
        }

        @Override
        public String toString() {
            return "vdlMsgData{" + "data=" + data + ", time=" + time + '}';
        }        
    }
    

}
