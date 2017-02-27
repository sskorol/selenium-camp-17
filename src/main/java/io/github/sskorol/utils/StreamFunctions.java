package io.github.sskorol.utils;

import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.function.BinaryOperator.maxBy;
import static java.util.function.BinaryOperator.minBy;

public final class StreamFunctions {

	public static <T, U extends Comparable<U>> BinaryOperator<T> min(final Function<T, U> condition) {
		return minBy(comparing(condition));
	}

	public static <T, U extends Comparable<U>> BinaryOperator<T> max(final Function<T, U> condition) {
		return maxBy(comparing(condition));
	}

	private StreamFunctions() {
		throw new UnsupportedOperationException("Illegal access to private constructor!");
	}
}
