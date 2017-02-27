package io.github.sskorol.core.misc;

import org.testng.*;

public class BeforeMethodWebDriverProvider extends WebDriverProvider implements IInvokedMethodListener {

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.isTestMethod()) {
			this.setupDriver();
		}
	}

	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		if(method.isTestMethod()) {
			this.cleanUp();
		}
	}
}
