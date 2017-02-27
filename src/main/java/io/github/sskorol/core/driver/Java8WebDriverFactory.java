package io.github.sskorol.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.sskorol.utils.CapabilityFunctions.*;
import static io.github.sskorol.utils.Configuration.CoreConstants.*;
import static javaslang.API.*;
import static javaslang.Predicates.*;

public class Java8WebDriverFactory {

	public static WebDriver getDriverUsingMatcherAndCommonFunctions() {
		return Match(System.getProperty("browser")).of(
				Case(anyOf(isNull(), String::isEmpty), () -> { throw new IllegalStateException("'browser' property is missing!"); }),
				Case(CHROME::equalsIgnoreCase, () -> new ChromeDriver()),
				Case(FIREFOX::equalsIgnoreCase, () -> new FirefoxDriver()),
				Case($(), browser -> { throw new IllegalArgumentException(browser + " browser is not supported!"); }));
	}

	public static WebDriver getDriverUsingMatcherAndCustomFunctions(DesiredCapabilities capabilities) {
		return Match(capabilities).of(
				Case(isNull(), () -> { throw new IllegalStateException("DesiredCapabilities are missing!"); }),
				Case(hasNoBrowser, () -> { throw new IllegalArgumentException("'browser' capability is missing!"); }),
				Case(isChrome, caps -> new ChromeDriver(caps)),
				Case(isFirefox, caps -> new FirefoxDriver(caps)),
				Case($(), caps -> { throw new IllegalArgumentException(caps.getBrowserName() + " browser is not supported!"); }));
	}
}
