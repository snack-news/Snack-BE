package com.snack.news.matcher;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.List;

public class ContainsInAnyOrder<T> extends TypeSafeMatcher<List<T>> {

	private List<T> items;

	public ContainsInAnyOrder(List<T> items) {
		this.items = items;
	}

	@Override
	protected boolean matchesSafely(List<T> items) {
		if (this.items.size() != items.size()) {
			return false;
		}
		return this.items.containsAll(items);
	}


	@Factory
	public static <T> Matcher<List> containsInAnyOrder(List<T> list) {
		return new ContainsInAnyOrder(list);
	}

	@Override
	public void describeTo(Description description) {

	}
}
