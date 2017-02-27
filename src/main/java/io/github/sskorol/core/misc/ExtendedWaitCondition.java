package io.github.sskorol.core.misc;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum ExtendedWaitCondition {

	visible(ExpectedConditions::visibilityOfElementLocated),
	enabled(ExpectedConditions::elementToBeClickable),
	selected(ExpectedConditions::elementToBeSelected),
	allVisible(ExpectedConditions::visibilityOfAllElementsLocatedBy);

	private final Function<By, ExpectedCondition<?>> type;
}
