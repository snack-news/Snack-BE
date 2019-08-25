package com.snack.news.fixture;

import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import com.snack.news.dto.TopicDto;

import java.util.concurrent.ThreadLocalRandom;

public abstract class TopicFixture {
	protected final static Topic DUMMY = Topic.builder().build();
	protected final static TopicDto TEST_TOPIC_DTO_FOR_CORRECT_REQUEST;
	protected final static Topic SOME_SAVED_TEST_TOPIC;

	static {
		final String randomTopicName = Long.toString(ThreadLocalRandom.current().nextLong());
		final TopicType testTopicType = TopicType.CORP;
		TEST_TOPIC_DTO_FOR_CORRECT_REQUEST = TopicDto.builder().name(randomTopicName).type(testTopicType).build();
		SOME_SAVED_TEST_TOPIC = TopicDto.builder().id(999L).name(randomTopicName).type(testTopicType).build().getTopicUpdateEntity();
	}
}
