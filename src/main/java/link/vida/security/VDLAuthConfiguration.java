package link.vida.security;

import java.util.HashMap;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;

public class VDLAuthConfiguration extends Configuration {

    static final public String APP_NAME = "VDL";

    private final AppConfigurationEntry[] demoEntries;

    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        if (name.equals(APP_NAME)) {
            return demoEntries;
        } else {
            return null;
        }
    }

    VDLAuthConfiguration() {
        demoEntries = new AppConfigurationEntry[]{
            new AppConfigurationEntry(VDLLoginModule.class.getName(),
            AppConfigurationEntry.LoginModuleControlFlag.REQUIRED,
            new HashMap<String, String>() {
                {
                    put("debug", "true");
                }
            })
        };
    }

}
