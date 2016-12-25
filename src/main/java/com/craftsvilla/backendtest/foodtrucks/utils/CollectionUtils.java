package com.craftsvilla.backendtest.foodtrucks.utils;

import java.util.Collection;

public class CollectionUtils {

	public static <E extends Object> boolean isEmpty(Collection<E> collection) {

		return null == collection || collection.isEmpty();
	}

	public static <E extends Object> boolean isNotEmpty(Collection<E> collection) {

		return !isEmpty(collection);
	}
}
