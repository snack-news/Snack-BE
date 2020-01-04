package com.snack.news.repository;

import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class TopicRepositoryTest {
	@Autowired
	private TopicRepository topicRepository;

	@Test
	@DisplayName("토픽 타입에 해당하는 토픽 리스트를 반환한다")
	@Transactional
	void getTopicListTestByTopicType() {
		List<Topic> actualTopicList = topicRepository.findAllByTypeIs(TopicType.CORP);

		List<Topic> allTopicList = topicRepository.findAll();
		List<Topic> expectedTopicList = allTopicList.stream()
				.filter(t -> t.getType().equals(TopicType.CORP))
				.collect(toList());

		assertThat(actualTopicList).isEqualTo(expectedTopicList);
	}

	@Test
	@DisplayName("토픽ID 리스트에 해당하는 토픽 리스트를 반환한다")
	@Transactional
	void getTopicListTestByTopicId() {
		final List<Long> topicIds = Arrays.asList(1L, 3L);
		List<Topic> actualTopicList = topicRepository.findByIdIn(topicIds);

		List<Topic> allTopicList = topicRepository.findAll();
		List<Topic> expectedTopicList = allTopicList.stream()
				.filter(t -> topicIds.contains(t.getId()))
				.collect(toList());

		assertThat(actualTopicList).isEqualTo(expectedTopicList);
	}
}
