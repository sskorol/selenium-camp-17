package io.github.sskorol.model;

import lombok.*;

import javax.money.MonetaryAmount;

@Data
public class Product {

	private final String name;
	private final MonetaryAmount oldPrice;
	private final MonetaryAmount newPrice;
	private final double discount;

	public boolean hasDiscount() {
		return discount > 0.0;
	}
}
