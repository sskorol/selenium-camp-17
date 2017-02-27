package io.github.sskorol.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.github.sskorol.utils.Configuration.CoreConstants.*;

public class Java7WebDriverFactory {

	public static WebDriver getDriverUsingSwitch() {
		final String browser = System.getProperty("browser");

		if (browser == null || browser.isEmpty()) {
			throw new IllegalStateException("'browser' property is missing!");
		}

		switch (browser) {
			case CHROME:
				return new ChromeDriver();
			case FIREFOX:
				return new FirefoxDriver();
			default:
				throw new IllegalArgumentException(browser + " browser is not supported!");
		}
	}

	public static WebDriver getDriverUsingIf(DesiredCapabilities desiredCapabilities) {
		if (desiredCapabilities == null) {
			throw new IllegalStateException("DesiredCapabilities are missing!");
		}

		final String browser = desiredCapabilities.getBrowserName();

		if (CHROME.equalsIgnoreCase(browser)) {
			return new ChromeDriver(desiredCapabilities);
		} else if (FIREFOX.equalsIgnoreCase(browser)) {
			return new FirefoxDriver(desiredCapabilities);
		} else if (browser.isEmpty()) {
			throw new IllegalStateException("'browser' capability is missing!");
		}

		throw new IllegalArgumentException(desiredCapabilities.getBrowserName() + " browser is not supported!");
	}
}
