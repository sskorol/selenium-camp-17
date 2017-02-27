package io.github.sskorol.pages;

import io.github.sskorol.core.wrappers.BasePage;
import io.github.sskorol.model.Product;
import lombok.val;
import org.jsoup.nodes.Element;

import java.util.Optional;
import java.util.function.BinaryOperator;

public class Java8ProductsPage extends BasePage {

	public Optional<Product> getProduct(BinaryOperator<Product> reductionOperator) {
		return getAll(".products-list-item")
				.map(this::toProduct)
				.filter(Product::hasDiscount)
				.reduce(reductionOperator);
	}

	private Product toProduct(Element element) {
		val name = textOf(element, ".products-list-item__brand").getOrElse("Unknown");
		val discount = discountOf(element, ".product-label", label -> !"NEW".equalsIgnoreCase(label));
		val newPrice = priceOf(element, ".price__new");
		val oldPrice = priceOf(element, ".price");
		return new Product(name, oldPrice, newPrice, discount);
	}
}
