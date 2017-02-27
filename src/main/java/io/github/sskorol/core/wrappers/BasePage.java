package io.github.sskorol.core.wrappers;

import io.github.sskorol.core.misc.Page;
import io.github.sskorol.utils.MoneyUtils;
import javaslang.control.Option;
import javaslang.control.Try;
import one.util.streamex.StreamEx;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;

import javax.money.MonetaryAmount;
import java.util.Objects;
import java.util.function.Predicate;

import static io.github.sskorol.core.misc.WebDriverProvider.getDriver;

public abstract class BasePage implements Page {

	private final WebDriver driver;

	public BasePage() {
		this.driver = getDriver();
	}

	protected StreamEx<Element> getAll(final String selector) {
		return StreamEx.of(getPageSource().select(selector).iterator());
	}

	protected Document getPageSource() {
		return Try.of(() -> Jsoup.parse(driver.getPageSource()))
				  .getOrElseThrow(ex -> new IllegalStateException("Unable to parse page source!", ex));
	}

	protected Option<String> textOf(final Element rootElement, final String selector) {
		return Option.of(rootElement.select(selector))
					 .map(Elements::first)
					 .filter(Objects::nonNull)
					 .map(Element::text);
	}

	protected MonetaryAmount priceOf(final Element rootElement, final String selector) {
		return textOf(rootElement, selector)
				.map(MoneyUtils::moneyOf)
				.getOrElse(MoneyUtils::zeroAmount);
	}

	protected double discountOf(final Element rootElement, final String selector, final Predicate<String> condition) {
		return textOf(rootElement, selector)
				.filter(condition)
				.map(MoneyUtils::discountOf)
				.getOrElse(MoneyUtils::zeroDiscount);
	}

	@Override
	public void navigateTo(final String url) {
		Try.run(() -> driver.get(url)).onFailure(ex -> {
			throw new IllegalArgumentException("Unable to open " + url, ex);
		});
	}
}
