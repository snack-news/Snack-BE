package com.snack.news.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class EqualsInOrder<T> extends TypeSafeMatcher<List<T>> {

	private List<T> items;

	public EqualsInOrder(List<T> items) {
		this.items = items;
	}

	@Override
	protected boolean matchesSafely(List<T> items) {
		if (this.items.size() != items.size()) {
			return false;
		}

		for (int i = 0; i < items.size(); i++) {
			if (!this.items.get(i).equals(items.get(i))) {
				return false;
			}
		}

		return true;
	}

	@Factory
	public static <T> Matcher<List> equalsInOrder(List<T> list) {
		return new EqualsInOrder(list);
	}

	@Override
	public void describeTo(Description description) {

	}
}
