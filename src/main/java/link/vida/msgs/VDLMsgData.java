/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.msgs;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Date;
import link.vida.utils.KeepAsJsonDeserialzier;

/**
 *
 * @author dcaro
 */
public class VDLMsgData {
    
    
    //http://stackoverflow.com/questions/4783421/how-can-i-include-raw-json-in-an-object-using-jackson
    @JsonDeserialize( using = KeepAsJsonDeserialzier.class )
    private String data;
    private  Date time;

    public VDLMsgData() {
    }
    
    
    public VDLMsgData(String data, Date time) {
        super();
        this.data = data;
        this.time = time;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    
    
    @Override
    public String toString() {
        return "vdlMsgData{" + "data=" + data + ", time=" + time + '}';
    }
}
