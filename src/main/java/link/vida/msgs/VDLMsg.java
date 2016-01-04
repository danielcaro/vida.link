/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.msgs;

/**
 *
 * @author dcaro
 */
public class VDLMsg {

    private String vdlClass;
    private VDLMsgData vdlMsg;

    public VDLMsg() {
    }

    public VDLMsg(String vdlClass, VDLMsgData vdlMsg) {
        super();
        this.vdlClass = vdlClass;
        this.vdlMsg = vdlMsg;
    }

    public String getVdlClass() {
        return vdlClass;
    }

    public void setVdlClass(String vdlClass) {
        this.vdlClass = vdlClass;
    }

    public VDLMsgData getVdlMsg() {
        return vdlMsg;
    }

    public void setVdlMsg(VDLMsgData vdlMsg) {
        this.vdlMsg = vdlMsg;
    }

    @Override
    public String toString() {
        return "VDLMsg{" + "vdlClass=" + vdlClass + ", vdlMsg=" + vdlMsg + '}';
    }

}
