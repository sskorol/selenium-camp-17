package io.github.sskorol.core.misc;

import lombok.val;
import one.util.streamex.StreamEx;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.function.UnaryOperator;

import static one.util.streamex.StreamEx.iterate;
import static org.joor.Reflect.on;

public final class PageFactory {

	public static <T extends Page> T open(final Class<T> pageClass) {
		val page = $(pageClass);
		page.openPage();
		return page;
	}

	public static <T extends Page> T $(final Class<T> pageClass) {
		return initElements(on(pageClass).create().get());
	}

	private static <T extends Page> T initElements(final T pageObject) {
		iterate(pageObject.getClass(), (UnaryOperator<Class>) Class::getSuperclass)
				.takeWhile(page -> page != Object.class)
				.flatMap(page -> StreamEx.of(page.getDeclaredFields()))
				.filter(field -> field.isAnnotationPresent(Find.class))
				.forEach(field -> initElement(pageObject, field));
		return pageObject;
	}

	private static <T extends Page> void initElement(final T pageObject, final Field field) {
		val fieldValue = on(field.getType()).create(getArgs(field.getAnnotation(Find.class))).get();
		on(pageObject).set(field.getName(), fieldValue);
	}

	private static Object[] getArgs(final Annotation annotation) {
		return StreamEx.of(annotation.annotationType().getDeclaredFields())
					   .map(method -> on(annotation).call(method.getName()).get())
					   .toArray();
	}

	private PageFactory() {
		throw new UnsupportedOperationException("Illegal access to private constructor!");
	}
}
