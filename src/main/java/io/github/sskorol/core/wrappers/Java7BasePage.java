package io.github.sskorol.core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.github.sskorol.core.misc.WebDriverProvider.getDriver;
import static io.github.sskorol.utils.Configuration.CoreConstants.WAIT_TIMEOUT;

public abstract class Java7BasePage {

	private final WebDriverWait wait;

	public Java7BasePage() {
		this.wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT);
	}

	private WebElement waitUntilElementIsVisible(By locator) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected void click(By locator) {
		waitUntilElementIsVisible(locator).click();
	}

	protected void type(By locator, CharSequence text) {
		waitUntilElementIsVisible(locator).sendKeys(text);
	}
}
