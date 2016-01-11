/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db.vdl.mappers;

import link.vida.db.vdl.models.Auth;
import link.vida.db.vdl.models.AuthKey;

/**
 * 
 * @author dcaro
 * https://objectpartners.com/2011/04/05/using-mybatis-annotations-with-spring-3-0-and-maven/
 * 
 */
//@CacheNamespace(implementation=org.mybatis.caches.ehcache.EhBlockingCache.class)
public interface AuthsMapper {

//    @Select("SELECT * FROM auths WHERE auth_token = '${authToken}' AND auth_id = '${authId}' limit 1;")
    // @Param("authToken") String authToken,@Param("authId")  String authId
    public Auth selectByPrimaryKey(AuthKey authKey);
    
}
