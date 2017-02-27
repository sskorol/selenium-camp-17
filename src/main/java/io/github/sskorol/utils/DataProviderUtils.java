package io.github.sskorol.utils;

import javaslang.Tuple;
import one.util.streamex.StreamEx;

import java.util.Iterator;
import java.util.function.Function;

public final class DataProviderUtils {

	public static Iterator<Object[]> combine(Tuple... data) {
		return combine(d -> d.toSeq().toJavaArray(), data);
	}

	@SafeVarargs
	public static <T> Iterator<Object[]> combine(T... data) {
		return combine(d -> new Object[]{d}, data);
	}

	@SafeVarargs
	private static <T> Iterator<Object[]> combine(Function<T, Object[]> transformer, T... data) {
		return StreamEx.of(data).map(transformer).iterator();
	}

	private DataProviderUtils() {
		throw new UnsupportedOperationException("Illegal access to private constructor!");
	}
}
