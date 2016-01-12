
package link.vida.crsh;

import java.util.Set;

import org.crsh.plugin.CRaSHPlugin;
import org.crsh.plugin.PluginDiscovery;

import com.google.inject.Inject;

public class GuicePluginDiscovery implements PluginDiscovery {

	private final Set<CRaSHPlugin<?>> plugins;

	@Inject
	private GuicePluginDiscovery(Set<CRaSHPlugin<?>> plugins) {
		this.plugins = plugins;
	}
	
	@Override
	public Iterable<CRaSHPlugin<?>> getPlugins() {
		return plugins;
	}

}
