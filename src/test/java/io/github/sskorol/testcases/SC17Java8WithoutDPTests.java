package io.github.sskorol.testcases;

import io.github.sskorol.model.Product;
import io.github.sskorol.pages.Java8ProductsPage;
import lombok.val;
import org.testng.annotations.Test;

import static io.github.sskorol.core.misc.PageFactory.open;
import static io.github.sskorol.utils.MoneyUtils.moneyOf;
import static io.github.sskorol.utils.StreamFunctions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SC17Java8WithoutDPTests {

	@Test
	public void productDiscountShouldMatchNewPrice() {
		val actualProduct = open(Java8ProductsPage.class).getProduct(max(Product::getDiscount));

		assertThat(actualProduct)
				.isNotEmpty()
				.map(p -> p.getOldPrice().subtract(p.getOldPrice().multiply(p.getDiscount())))
				.hasValue(moneyOf("477 грн."));
	}
}
