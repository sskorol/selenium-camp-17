package io.github.sskorol.utils;

import javaslang.control.Try;
import lombok.val;
import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public final class MoneyUtils {

	private static final String CURRENCY_REPRESENTATION = "грн.";
	private static final Locale UA_LOCALE = Locale.forLanguageTag("uk-UA");
	private static final NumberFormat FORMATTER = DecimalFormat.getInstance(UA_LOCALE);
	private static final CurrencyUnit CURRENCY_UNIT = Monetary.getCurrency(UA_LOCALE);

	static {
		val customSymbols = new DecimalFormatSymbols();
		customSymbols.setDecimalSeparator('.');
		customSymbols.setGroupingSeparator(' ');
		((DecimalFormat) FORMATTER).setDecimalFormatSymbols(customSymbols);
		FORMATTER.setGroupingUsed(true);
	}

	public static MonetaryAmount moneyOf(final String amount) {
		return Try.of(() -> Money.of(FORMATTER.parse(amount), CURRENCY_UNIT))
				  .getOrElseThrow(ex -> new IllegalArgumentException("Unable to parse " + amount, ex));
	}

	public static MonetaryAmount zeroAmount() {
		return moneyOf("0.00 " + CURRENCY_REPRESENTATION);
	}

	public static double discountOf(final String text) {
		return Try.of(() -> NumberFormat.getPercentInstance().parse(text))
				  .map(num -> Math.abs(num.doubleValue()))
				  .getOrElseGet(ex -> 0.0);
	}

	public static double zeroDiscount() {
		return 0.0;
	}

	private MoneyUtils() {
		throw new UnsupportedOperationException("Illegal access to private constructor");
	}
}
