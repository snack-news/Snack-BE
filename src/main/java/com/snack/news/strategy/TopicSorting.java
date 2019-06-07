package com.snack.news.strategy;

import com.snack.news.domain.Topic;

import java.util.Comparator;

public enum TopicSorting {
	NAME(Comparator.comparing(Topic::getName));

	private Comparator<Topic> operator;

	TopicSorting(Comparator<Topic> operator) {
		this.operator = operator;
	}

	public Comparator<Topic> getOperator() {
		return operator;
	}
}
