package io.github.sskorol.utils;

import lombok.val;
import org.apache.commons.configuration2.CompositeConfiguration;
import org.apache.commons.configuration2.EnvironmentConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.net.URL;
import java.util.Collections;

import static java.util.Optional.ofNullable;

public final class Configuration {

	private static final CompositeConfiguration COMPOSITE_CONFIGURATION;

	static {
		try {
			COMPOSITE_CONFIGURATION = new CompositeConfiguration(Collections.singletonList(
					getPropertiesConfiguration("properties/application.properties")));
			COMPOSITE_CONFIGURATION.addConfiguration(new SystemConfiguration());
			COMPOSITE_CONFIGURATION.addConfiguration(new EnvironmentConfiguration());
		} catch (ConfigurationException e) {
			throw new IllegalArgumentException("Unable to load configuration.");
		}
	}

	public static class CoreConstants {

		public static final long WAIT_TIMEOUT = getLong("wait.timeout.arg");
		public static final long POLLING_INTERVAL = getLong("polling.interval.arg");
		public static final String SITE_URL = getString("site.url.arg", "http://localhost");
		public static final String FIREFOX = "firefox";
		public static final String CHROME = "chrome";

		private CoreConstants() {
			throw new UnsupportedOperationException("Illegal access to private constructor!");
		}
	}

	private Configuration() {
		throw new UnsupportedOperationException("Illegal access to private constructor.");
	}

	public static String getString(final String key, final String defaultValue) {
		return COMPOSITE_CONFIGURATION.getString(key, defaultValue);
	}

	public static String getString(final String key) {
		return getString(key, "unknown");
	}

	public static int getInt(final String key, final int defaultValue) {
		return COMPOSITE_CONFIGURATION.getInt(key, defaultValue);
	}

	public static int getInt(final String key) {
		return getInt(key, -1);
	}

	public static long getLong(final String key, final long defaultValue) {
		return COMPOSITE_CONFIGURATION.getLong(key, defaultValue);
	}

	public static long getLong(final String key) {
		return getLong(key, -1L);
	}

	public static boolean getBool(final String key, final boolean defaultValue) {
		return COMPOSITE_CONFIGURATION.getBoolean(key, defaultValue);
	}

	public static boolean getBool(final String key) {
		return getBool(key, false);
	}

	private static PropertiesConfiguration getPropertiesConfiguration(final String fileName) throws ConfigurationException {
		val propertiesPath = ofNullable(ClassLoader.getSystemClassLoader().getResource(fileName))
				.map(URL::getFile)
				.orElseThrow(IllegalArgumentException::new);

		return new FileBasedConfigurationBuilder<>(PropertiesConfiguration.class)
				.configure(new Parameters().properties().setFile(new File(propertiesPath)))
				.getConfiguration();
	}
}
