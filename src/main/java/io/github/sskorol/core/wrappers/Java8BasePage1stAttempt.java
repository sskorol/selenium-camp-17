package io.github.sskorol.core.wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Function;

import static io.github.sskorol.core.misc.WebDriverProvider.getDriver;
import static io.github.sskorol.utils.Configuration.CoreConstants.WAIT_TIMEOUT;

public abstract class Java8BasePage1stAttempt {

	private final WebDriverWait wait;

	public Java8BasePage1stAttempt() {
		this.wait = new WebDriverWait(getDriver(), WAIT_TIMEOUT);
	}

	private WebElement waitFor(By locator, Function<By, ExpectedCondition<WebElement>> condition) {
		return wait.until(condition.apply(locator));
	}

	protected void click(By locator) {
		waitFor(locator, ExpectedConditions::elementToBeClickable).click();
	}

	protected void type(By locator, CharSequence text) {
		waitFor(locator, ExpectedConditions::visibilityOfElementLocated).sendKeys(text);
	}
}
