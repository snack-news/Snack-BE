package com.snack.news.util;

import java.util.List;

public class CollectionHelper {
	public static  <E> E getLastElementInList(List<E> list) {
		return list.get(list.size() - 1);
	}
}
