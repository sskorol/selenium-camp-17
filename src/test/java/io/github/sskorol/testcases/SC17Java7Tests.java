package io.github.sskorol.testcases;

import io.github.sskorol.model.Product;
import io.github.sskorol.pages.Java7ProductsPage;
import org.testng.annotations.Test;

import java.util.Comparator;

import static io.github.sskorol.core.misc.PageFactory.open;
import static io.github.sskorol.utils.MoneyUtils.moneyOf;
import static org.assertj.core.api.Assertions.assertThat;

public class SC17Java7Tests {

	@Test
	public void productDiscountMatchNewPriceViaJava7() {
		Comparator<Product> productComparator = new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				return Double.compare(p1.getDiscount(), p2.getDiscount());
			}
		};

		Product actualProduct = open(Java7ProductsPage.class).getProduct(productComparator);

		assertThat(actualProduct)
				.isNotNull()
				.extracting(p -> p.getOldPrice().subtract(p.getOldPrice().multiply(p.getDiscount())))
				.isEqualTo(moneyOf("477 грн."));
	}
}
