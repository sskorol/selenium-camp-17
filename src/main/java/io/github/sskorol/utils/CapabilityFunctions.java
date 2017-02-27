package io.github.sskorol.utils;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.function.Function;
import java.util.function.Predicate;

import static io.github.sskorol.utils.Configuration.CoreConstants.CHROME;
import static io.github.sskorol.utils.Configuration.CoreConstants.FIREFOX;

public final class CapabilityFunctions {

	public static final Predicate<DesiredCapabilities> hasBrowser = caps -> !caps.getBrowserName().isEmpty();
	public static final Predicate<DesiredCapabilities> hasNoBrowser = hasBrowser.negate();
	public static final Function<DesiredCapabilities, String> browserOf = DesiredCapabilities::getBrowserName;
	public static final Function<DesiredCapabilities, Predicate<String>> isBrowserEqual = browserOf.andThen(CapabilityFunctions::areEqual);
	public static final Predicate<DesiredCapabilities> isChrome = caps -> isBrowserEqual.apply(caps).test(CHROME);
	public static final Predicate<DesiredCapabilities> isFirefox = caps -> isBrowserEqual.apply(caps).test(FIREFOX);

	private static Predicate<String> areEqual(final String value2) {
		return value -> value.equalsIgnoreCase(value2);
	}

	private CapabilityFunctions() {
		throw new UnsupportedOperationException("Illegal access to private constructor!");
	}
}
