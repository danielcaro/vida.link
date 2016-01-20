/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package link.vida.crash;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import org.crsh.plugin.CRaSHPlugin;
import org.crsh.plugin.PluginDiscovery;
import org.crsh.plugin.ServiceLoaderDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dcaro
 */
public class CrashPluginsModule extends AbstractModule {

    private static final Logger log = LoggerFactory.getLogger(CrashPluginsModule.class);

    @Override
    protected void configure() {
        ClassLoader loader = getClass().getClassLoader();
        PluginDiscovery discovery = new ServiceLoaderDiscovery(loader);

        Multibinder<CRaSHPlugin<?>> pluginBinder
                = Multibinder.newSetBinder(binder(), 
                        new TypeLiteral<CRaSHPlugin<?>>() {});

        Iterable<CRaSHPlugin<?>> plugins = discovery.getPlugins();
        bind(PluginDiscovery.class).to(GuicePluginDiscovery.class);
        for (CRaSHPlugin<?> plugin : plugins) {
            pluginBinder.addBinding().toInstance(plugin);
            log.info("Binding CRaSHPlugin [" + plugin.getClass().getSimpleName() + "]"+ plugin.getType() );
            bind((Class<CRaSHPlugin>) plugin.getClass()).toInstance(plugin);
        }
    }
}
