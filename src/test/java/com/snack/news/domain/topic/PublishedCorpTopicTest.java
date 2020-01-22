package com.snack.news.domain.topic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.assertj.core.api.Assertions.assertThat;

class PublishedCorpTopicTest {

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("회사 이름이 잘못 입력될 경우 false를 반환한다.")
	void isPublishedCorp(String name) {
		assertThat(PublishedCorpTopic.isPublishedCorp(name)).isFalse();
	}
}