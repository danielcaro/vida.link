/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class AliasEnhancedWebAppContext extends WebAppContext {

    
    final Logger logger = LoggerFactory.getLogger(AliasEnhancedWebAppContext.class);
    
    
    public AliasEnhancedWebAppContext(String webApp, String contextPath) {
        super(webApp, contextPath);
    }
    
    
    


    public String getResourceAlias2(String alias){
        logger.info("getResourceAlias:(" + alias + ")");
        
//
//        for (Entry<String, String> oneAlias : 
//            resourceAliases.entrySet()) {
//            if (alias.startsWith(oneAlias.getKey())) {
//                String retorno = alias.replace(oneAlias.getKey(), oneAlias.getValue());
//                logger.info("GRA=("+ alias + ")[" + oneAlias.getKey() + "]["+oneAlias.getValue()+ "]Return :" + alias.replace(oneAlias.getKey(), oneAlias.getValue()));
////                return retorno;
//            }
//        }
//
    final String classDir = this.getClass().getClassLoader().getResource("link/vida").toExternalForm();
    
        if(alias.startsWith("/WEB-INF/classes/")){            
            String retorno = alias.replace("/WEB-INF/classes/", "");
            logger.info("GRA2=("+ alias + ")Return :" + classDir);
            return retorno;
        }

        return null;    
    }
    

//    @Override
    public String getResourceAlias3(String alias) {        
        logger.info("getResourceAlias:" + alias);

        @SuppressWarnings("unchecked")
        Map<String, String> resourceAliases = (Map<String, String>) getResourceAliases();

        if (resourceAliases == null) {
            return null;
        }

        for (Entry<String, String> oneAlias : 
            resourceAliases.entrySet()) {
            logger.info("oneAlias:" + oneAlias.getKey());
            if (alias.startsWith(oneAlias.getKey())) {
                String retorno = alias.replace(oneAlias.getKey(), oneAlias.getValue());
                logger.info("GRA=("+ alias + ")[" + oneAlias.getKey() + "]["+oneAlias.getValue()+ "]Return :" + retorno);
                return retorno;
            }
        }

        return null;
    }
    
    
    
}