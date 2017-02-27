package io.github.sskorol.testcases;

import io.github.sskorol.model.Product;
import io.github.sskorol.pages.Java8ProductsPage;
import javaslang.Tuple;
import lombok.val;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.money.MonetaryAmount;
import java.util.Iterator;
import java.util.function.BinaryOperator;

import static io.github.sskorol.core.misc.PageFactory.open;
import static io.github.sskorol.utils.DataProviderUtils.*;
import static io.github.sskorol.utils.MoneyUtils.moneyOf;
import static io.github.sskorol.utils.StreamFunctions.max;
import static io.github.sskorol.utils.StreamFunctions.min;
import static org.assertj.core.api.Assertions.assertThat;

public class SC17Java8WithDPTests {

	@DataProvider
	public static Iterator<Object[]> getData() {
		return combine(
				Tuple.of(moneyOf("477 грн."), max(Product::getDiscount)),
				Tuple.of(moneyOf("1011 грн."), min(Product::getDiscount)));
	}

	@Test(dataProvider = "getData")
	public void productDiscountShouldMatchNewPrice(MonetaryAmount expectedPrice, BinaryOperator<Product> reductionOperator) {
		val actualProduct = open(Java8ProductsPage.class).getProduct(reductionOperator);

		assertThat(actualProduct)
				.isNotEmpty()
				.map(p -> p.getOldPrice().subtract(p.getOldPrice().multiply(p.getDiscount())))
				.hasValue(expectedPrice);
	}
}
