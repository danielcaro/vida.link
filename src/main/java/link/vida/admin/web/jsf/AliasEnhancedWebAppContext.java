/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.admin.web.jsf;

import java.util.Map;
import java.util.Map.Entry;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author dcaro
 */
public class AliasEnhancedWebAppContext extends WebAppContext {

    public AliasEnhancedWebAppContext(String webApp, String contextPath) {
        super(webApp, contextPath);
    }
    
    

    @Override
    public String getResourceAlias(String alias) {
        

        @SuppressWarnings("unchecked")
        Map<String, String> resourceAliases = (Map<String, String>) getResourceAliases();

        if (resourceAliases == null) {
            return null;
        }

        for (Entry<String, String> oneAlias : 
            resourceAliases.entrySet()) {

            if (alias.startsWith(oneAlias.getKey())) {
                return alias.replace(oneAlias.getKey(), oneAlias.getValue());
            }
        }

        return null;
    }
    
    
    
}