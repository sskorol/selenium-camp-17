package io.github.sskorol.pages;

import io.github.sskorol.core.wrappers.Java8BasePage2ndAttempt;
import org.openqa.selenium.By;

import static io.github.sskorol.core.wrappers.WaitCondition.visible;

public class LoginPage extends Java8BasePage2ndAttempt {

	private By buttonLogin = By.id("login");

	public LoginPage login() {
		click(buttonLogin, visible);
		return this;
	}
}
