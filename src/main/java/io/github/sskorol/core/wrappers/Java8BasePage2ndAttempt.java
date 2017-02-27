package io.github.sskorol.core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import static io.github.sskorol.core.misc.WebDriverProvider.getDriver;
import static io.github.sskorol.core.wrappers.WaitCondition.*;
import static io.github.sskorol.utils.Configuration.CoreConstants.WAIT_TIMEOUT;

public abstract class Java8BasePage2ndAttempt {

	private final WebDriverWait wait;

	public Java8BasePage2ndAttempt() {
		this.wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT);
	}

	private WebElement waitFor(By locator, WaitCondition condition) {
		return wait.until(condition.getType().apply(locator));
	}

	protected void click(By locator) {
		click(locator, enabled);
	}

	protected void click(By locator, WaitCondition condition) {
		waitFor(locator, condition).click();
	}

	protected void type(By locator, CharSequence text) {
		waitFor(locator, visible).sendKeys(text);
	}
}
