package com.snack.news.domain.topic;

import java.util.Arrays;

public enum PublishedCorpTopic {
	WOOWA_BROS("우아한형제들"),
	VIVA_REPUBLICA("비바리퍼블리카"),
	WEMAKEPRICE("위메프"),
	COUPANG("쿠팡"),
	YANOLJA("야놀자"),
	TMON("티몬"),
	TADA("타다");

	private String name;

	PublishedCorpTopic(String name) {
		this.name = name;
	}

	public static boolean isPublishedCorp(String name) {
		return Arrays.stream(PublishedCorpTopic.values())
				.anyMatch(topic -> topic.name.equals(name));
	}

	public String getName() {
		return name;
	}
}
