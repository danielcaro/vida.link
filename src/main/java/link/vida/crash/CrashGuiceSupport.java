package link.vida.crash;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.crsh.plugin.PluginContext;
import org.crsh.plugin.PluginDiscovery;
import org.crsh.plugin.PluginLifeCycle;
import org.crsh.plugin.PropertyDescriptor;
import org.crsh.vfs.FS;
import org.crsh.vfs.Path;

import com.google.common.collect.ImmutableMap;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.crsh.auth.AuthenticationPlugin;
import org.crsh.ssh.SSHPlugin;

public class CrashGuiceSupport extends AbstractModule {

    private static final String AUTOSTART = "autostart";

    public static class Bootstrap extends PluginLifeCycle {

        private final Injector injector;
        private ClassLoader loader;
        private final CrashGuiceConfiguration configuration;
        private PluginContext context;

        @Inject
        public Bootstrap(Injector injector, PluginDiscovery pluginDiscovery, CrashGuiceConfiguration configuration,
                @Named(AUTOSTART) Boolean autostart) throws IOException, URISyntaxException {
            this.injector = injector;
            this.loader = getClass().getClassLoader();
            this.configuration = configuration;
            FS cmdFS = createCommandFS();
            FS confFS = createConfFS();

            context = new PluginContext(
                    pluginDiscovery,
                    buildGuiceMap(),
                    cmdFS,
                    confFS,
                    loader);

            for (Map.Entry<PropertyDescriptor<Object>, Object> property : configuration.toEntries()) {
                context.setProperty(property.getKey(), property.getValue());
            }

            if (autostart) {
                start();
            }
        }

        public void start() {
            context.refresh();
            start(context);
        }

        private Map<String, Object> buildGuiceMap() {
            return ImmutableMap.of(
                    "factory", injector,
                    "properties", configuration,
                    "beans", new GuiceMap(injector)
            );
        }

        protected FS createCommandFS() throws IOException, URISyntaxException {
            FS cmdFS = new FS();
            cmdFS.mount(loader, Path.get("/crash/commands/"));
            cmdFS.mount(loader, Path.get("/crash/commands/guice/"));
            return cmdFS;
        }

        protected FS createConfFS() throws IOException, URISyntaxException {
            FS confFS = new FS();
            confFS.mount(loader, Path.get("/crash/"));
            return confFS;
        }

        public void destroy() throws Exception {
            stop();
        }
    }

    private final boolean autostart;

    public CrashGuiceSupport(boolean autostart) {
        this.autostart = autostart;
    }

    public CrashGuiceSupport() {
        this(true);
    }

    @Override
    protected void configure() {
        install(new CrashPluginsModule());
        bind(Boolean.class).annotatedWith(Names.named(AUTOSTART)).toInstance(autostart);
        bind(Bootstrap.class).asEagerSingleton();
    }

    

    @Provides
    public CrashGuiceConfiguration crashConfiguration() {
        // http://www.edc4it.com/blog/java/understanding-java-security-and-jaas-part-3-a-custom-login-module.html
        // https://thedet.wordpress.com/2011/01/23/guice-2-0-multibinder-java-serviceloader-plugin-mechanism/
        
        return CrashGuiceConfiguration.builder()
                .property(SSHPlugin.SSH_PORT.getName(), 2222)
                .property(AuthenticationPlugin.AUTH.getName(), "vida.link.crash")
                .build();
    }

}
