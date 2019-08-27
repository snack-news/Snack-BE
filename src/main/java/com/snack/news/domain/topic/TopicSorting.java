package com.snack.news.domain.topic;

import java.util.Comparator;

public enum TopicSorting {
	NAME(Comparator.comparing(Topic::getName)),
	ID(Comparator.comparing(Topic::getId));

	private Comparator<Topic> operator;

	TopicSorting(Comparator<Topic> operator) {
		this.operator = operator;
	}

	public Comparator<Topic> getOperator() {
		return operator;
	}
}
