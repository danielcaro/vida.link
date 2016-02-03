/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db.vdl.mappers;

import java.util.List;
import link.vida.db.vdl.models.Peer;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Select;



/**
 * http://mybatis.co.uk/index.php/category/mybatis-annotations-examples
 * @author dcaro
 */
//@CacheNamespace(implementation=org.mybatis.caches.ehcache.EhBlockingCache.class)
public interface PeersMapper {
       
    @Select("SELECT * FROM peers WHERE id = #{id}")
    Peer selectPeer(int id);

    @Select("SELECT * FROM peers")
    List<Peer> selectPeers();    
    
}
