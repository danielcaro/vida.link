/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.utils;

import com.google.inject.Binding;
import com.google.inject.Injector;
import com.google.inject.Key;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import link.vida.app.VidaLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    public static byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    public static void showBindings(Injector injector) {
        final Map<Key<?>, Binding<?>> map = injector.getBindings();
        List<Key<?>> keys = new ArrayList<>(map.keySet());
        keys.stream().forEach(new Consumer<Key<?>>() {
            @Override
            public void accept(Key<?> key) {
                log.info(key.toString() + ": " + map.get(key));
            }
        });
    }

}
