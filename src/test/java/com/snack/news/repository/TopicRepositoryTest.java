package com.snack.news.repository;

import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TopicRepositoryTest {
	@Autowired
	private TopicRepository topicRepository;

	@Test
	@Transactional
	public void 토픽_타입에_해당하는_토픽_리스트를_반환한다() {
		List<Topic> actualTopicList = topicRepository.findAllByTypeIs(TopicType.CORP);

		List<Topic> allTopicList = topicRepository.findAll();
		List<Topic> expectedTopicList = allTopicList.stream()
				.filter(t -> t.getType().equals(TopicType.CORP))
				.collect(toList());

		assertThat(actualTopicList, equalTo(expectedTopicList));
	}

	@Test
	@Transactional
	public void 토픽ID_리스트에_해당하는_토픽_리스트를_반환한다() {
		final List<Long> topicIds = Arrays.asList(1L, 3L);
		List<Topic> actualTopicList = topicRepository.findByIdIn(topicIds);

		List<Topic> allTopicList = topicRepository.findAll();
		List<Topic> expectedTopicList = allTopicList.stream()
				.filter(t -> topicIds.contains(t.getId()))
				.collect(toList());

		assertThat(actualTopicList, equalTo(expectedTopicList));
	}
}
