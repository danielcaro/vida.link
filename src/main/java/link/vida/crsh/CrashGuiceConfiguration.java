
package link.vida.crsh;

import java.util.Collection;
import java.util.Map.Entry;

import org.crsh.plugin.PropertyDescriptor;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;

public class CrashGuiceConfiguration {


	private final ImmutableMap<PropertyDescriptor<Object>, Object> configuration;


	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		
		private Multimap<String, Object> values;

		private Builder() {
			values = ArrayListMultimap.create();
		}
		
		public Builder property(String propertyName, Object value) {
			values.put(propertyName, value);
			return this;
		}
		
		public CrashGuiceConfiguration build() {
			ImmutableMap.Builder<PropertyDescriptor<Object>, Object> configuration = ImmutableMap.builder();
			for (Entry<String, Collection<Object>> entry: values.asMap().entrySet()) {
				Collection<Object> values = entry.getValue();
				if (values.size() != 1) {
					throw new IllegalStateException("Duplicate entry for property : " + entry.getKey());
				}
				Object value = Iterables.getOnlyElement(values);
				configuration.put(new PropertyDescriptor(value.getClass(), entry.getKey(), value, "") {
					@Override
					protected Object doParse(String s) throws Exception {
						return s;
					}
				}, value);
				
			}
			return new CrashGuiceConfiguration(configuration.build());
		}
	}
	

	public CrashGuiceConfiguration(ImmutableMap<PropertyDescriptor<Object>, Object> configuration) {
		this.configuration = configuration;
	}

	
	public Iterable<Entry<PropertyDescriptor<Object>, Object>> toEntries() {
		return configuration.entrySet();
	}
	
}
