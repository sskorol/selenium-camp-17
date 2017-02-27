package io.github.sskorol.core.misc;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.github.sskorol.core.driver.Java8WebDriverFactory.*;
import static java.util.Optional.ofNullable;

public abstract class WebDriverProvider {

	private static final ThreadLocal<WebDriver> DRIVER_CONTAINER = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return DRIVER_CONTAINER.get();
	}

	protected void setupDriver() {
		WebDriverManager.getInstance(ChromeDriver.class).setup();
		DRIVER_CONTAINER.set(getDriverUsingMatcherAndCommonFunctions());
	}

	protected void cleanUp() {
		ofNullable(getDriver()).ifPresent(WebDriver::quit);
		DRIVER_CONTAINER.remove();
	}
}

