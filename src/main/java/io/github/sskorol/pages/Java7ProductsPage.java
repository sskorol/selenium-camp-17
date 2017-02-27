package io.github.sskorol.pages;

import io.github.sskorol.core.wrappers.ProductBasePage;
import io.github.sskorol.model.Product;
import io.github.sskorol.utils.MoneyUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import javax.money.MonetaryAmount;
import java.util.*;

import static io.github.sskorol.utils.MoneyUtils.moneyOf;
import static io.github.sskorol.utils.MoneyUtils.zeroAmount;
import static io.github.sskorol.utils.MoneyUtils.zeroDiscount;

public class Java7ProductsPage extends ProductBasePage {

	private By products = By.cssSelector(".products-list-item");
	private By productLabel = By.cssSelector(".product-label");
	private By productNewPrice = By.cssSelector(".price__new");
	private By productOldPrice = By.cssSelector(".price");
	private By productName = By.cssSelector(".products-list-item__brand");

	public Product getProduct(Comparator<Product> productComparator) {
		List<Product> productsList = new ArrayList<>();

		for (WebElement element : getAll(products))
			productsList.add(toProduct(element));

		Product product = Collections.max(productsList, productComparator);

		return product.hasDiscount() ? product : null;
	}

	private Product toProduct(WebElement element) {
		double discount;
		MonetaryAmount newPrice;

		String name = textOf(element, productName);
		String label = textOf(element, productLabel);
		String productPrice = textOf(element, productOldPrice);
		MonetaryAmount oldPrice = productPrice != null ? moneyOf(productPrice) : zeroAmount();

		if (label != null && !"NEW".equalsIgnoreCase(label)) {
			discount = MoneyUtils.discountOf(label);
			productPrice = textOf(element, productNewPrice);
			newPrice = productPrice != null ? moneyOf(productPrice) : zeroAmount();
		} else {
			discount = zeroDiscount();
			newPrice = zeroAmount();
		}

		return new Product(name != null ? name : "Unknown", oldPrice, newPrice, discount);
	}
}
