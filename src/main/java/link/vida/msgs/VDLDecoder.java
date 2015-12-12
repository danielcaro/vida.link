/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.msgs;


import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import link.vida.service.VDLPeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.*;
import com.google.gson.internal.bind.util.ISO8601Utils;
import java.text.ParsePosition;

/**
 *
 * @author dcaro
 */
public class VDLDecoder {

    private static final Logger log = LoggerFactory.getLogger(VDLDecoder.class);

    //  {"vdlMsg": {"data": "holasas", "time": "2015-12-08T10:05:31.575045Z"}, "vdlClass": "test"}
    public static Object getObject(String json) throws ParseException, ClassNotFoundException {
        JsonObject jObj =  new JsonParser().parse(json).getAsJsonObject() ;
        String className = jObj.get("class").getAsString();
        Date time = ISO8601Utils.parse(jObj.get("time").getAsString(), new ParsePosition(0));
        
        log.info("ClassName:" + className + " time:" + time  );
        
        // https://github.com/google/gson/blob/master/UserGuide.md
        // https://github.com/google/gson/blob/master/extras/src/main/java/com/google/gson/extras/examples/rawcollections/RawCollectionsExample.java
        Class clazz = Class.forName("link.vida.msgs." + className);
        Object obj = new Gson().fromJson(jObj.get("obj").getAsJsonObject(),clazz );

        return obj;
    }



}
