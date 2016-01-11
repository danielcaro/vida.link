/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.db.vdl;

import com.google.inject.Inject;
import java.util.List;
import link.vida.db.vdl.models.Auth;
import link.vida.db.vdl.models.Peer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import link.vida.db.vdl.models.AuthKey;
import link.vida.db.vdl.mappers.PeersMapper;
import link.vida.db.vdl.mappers.AuthsMapper;

/**
 *
 * @author dcaro
 */
public class VdlDao {

    private  final Logger log = LoggerFactory.getLogger(VdlDao.class);
    @Inject
    PeersMapper peersMapper;
    
    @Inject 
    AuthsMapper authMapper;
    
    
    public List<Peer> peersList(){        
        for(Peer peer: peersMapper.selectPeers()){
               log.info("PEER:" + peer);
        }
        return peersMapper.selectPeers();
    }
    
    
    public Auth findAuth(String token, String id){
        return authMapper.selectByPrimaryKey(new AuthKey(token, id));
    }
    

}
