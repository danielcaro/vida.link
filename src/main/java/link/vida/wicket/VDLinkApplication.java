/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.wicket;

import de.agilecoders.wicket.core.Bootstrap;
import de.agilecoders.wicket.core.settings.BootstrapSettings;
import de.agilecoders.wicket.core.settings.IBootstrapSettings;
import de.agilecoders.wicket.core.settings.ThemeProvider;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchTheme;
import de.agilecoders.wicket.themes.markup.html.bootswatch.BootswatchThemeProvider;
import org.apache.wicket.protocol.http.WebApplication;

public class VDLinkApplication extends WebApplication {

    public VDLinkApplication() {

    }

    @Override
    public void init() {
        super.init();

        final IBootstrapSettings settings = new BootstrapSettings();
        final ThemeProvider themeProvider = new BootswatchThemeProvider(BootswatchTheme.Lumen);

        settings.setThemeProvider(themeProvider);

        Bootstrap.install(this, settings);

    }

    /**
     * @return @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class getHomePage() {
        return VDLTemplate.class;
    }
}
