/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.utils;

import com.google.inject.Singleton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
@Singleton
public class FileConfig {
    
     private static final org.slf4j.Logger log = LoggerFactory.getLogger(FileConfig.class);

    public static Properties getPropertiesFromFile(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(fileName);
            prop.load(input);
            // inciar jdbcactive
            return prop;
        } catch (FileNotFoundException ex) {
            log.error(ex.getLocalizedMessage());
        } catch (IOException ex) {
            log.error(ex.getLocalizedMessage());
        }
        return null;
    }
        

    public static String getConfig(String property, String fileName) {
        return getPropertiesFromFile(fileName).getProperty(property);
    }

}
