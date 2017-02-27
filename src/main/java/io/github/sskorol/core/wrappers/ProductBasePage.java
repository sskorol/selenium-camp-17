package io.github.sskorol.core.wrappers;

import io.github.sskorol.core.misc.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

import static io.github.sskorol.core.misc.WebDriverProvider.getDriver;
import static io.github.sskorol.utils.Configuration.CoreConstants.WAIT_TIMEOUT;

public abstract class ProductBasePage implements Page {

	private final WebDriver driver;
	private final WebDriverWait wait;

	public ProductBasePage() {
		this.driver = getDriver();
		this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
	}

	protected List<WebElement> getAll(final By locator) {
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	protected String textOf(final WebElement rootElement, final By childElement) {
		try {
			return relativeOf(rootElement, childElement).getText();
		} catch (NoSuchElementException ignored) {
			// log it, re-throw, whatever
			return null;
		}
	}

	private WebElement relativeOf(final WebElement rootElement, final By childElement) {
		return rootElement.findElement(childElement);
	}

	@Override
	public void navigateTo(final String url) {
		driver.get(url);
	}
}
