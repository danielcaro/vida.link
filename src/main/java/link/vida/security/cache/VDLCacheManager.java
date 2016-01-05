/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.security.cache;

import org.apache.shiro.cache.ehcache.EhCacheManager;

/**
 *
 * @author daniel
 */
public class VDLCacheManager extends EhCacheManager{
    
    
    private final String cacheManagerConfigFile = "classpath:ehcache.xml";

    @Override
    public String getCacheManagerConfigFile() {
        return cacheManagerConfigFile;
    }
    
    
    
}
