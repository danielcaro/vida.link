/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.msgs;

import java.util.Date;

/**
 *
 * @author dcaro
 */
public class VDLTest {

    private final String data;
    private final Date time;

    public VDLTest(String data, Date time) {
        this.data = data;
        this.time = time;
    }

    @Override
    public String toString() {
        return "vdlTest{" + "data=" + data + ", time=" + time + '}';
    }

}
