package com.snack.news.service;

import com.snack.news.domain.Topic;
import com.snack.news.dto.TopicDto;
import com.snack.news.exception.TopicNotFoundException;
import com.snack.news.strategy.TopicSorting;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicServiceTest {
	private static final String TEST_TOPIC_TYPE = "CORP";
	private static final String TEST_TOPIC_NAME = "TEST_NAME";
	private static final String TEST_IMAGE_URL = "IMAGE_URL";

	@Autowired
	private TopicService topicService;

	@Test
	@Transactional
	public void 토픽들을_토픽명순으로_조회할_수_있다() {
		List<Topic> topicList = topicService.getTopicList(TopicSorting.NAME);
		assertThat(topicList.size()).as("데이터 없음").isNotZero();

		List<Topic> originTopicList = topicService.getTopicList(TopicSorting.ID);
		assertThat(topicList).doesNotContainSequence(originTopicList);

		List<Topic> sortedTopicList = originTopicList.stream().sorted(TopicSorting.NAME.getOperator()).collect(toList());
		assertThat(topicList).containsSequence(sortedTopicList);
	}

	@Test
	@Transactional
	public void 토픽을_등록_및_조회_할_수_있다() {
		TopicDto topicDto = TopicDto.builder().type(TEST_TOPIC_TYPE).name(TEST_TOPIC_NAME).image(TEST_IMAGE_URL).build();
		Topic topic = topicService.createTopic(topicDto);

		List<Topic> topicList = topicService.getTopicList(TopicSorting.NAME);

		assertThat(topicList).contains(topic);
	}

	@Test
	@Transactional
	public void 토픽을_수정_할_수_있다() {
		TopicDto topicDto = TopicDto.builder()
				.type(TEST_TOPIC_TYPE)
				.name(TEST_TOPIC_NAME)
				.image(TEST_IMAGE_URL).build();
		Topic createTopic = topicService.createTopic(topicDto);

		TopicDto updateTopicDto = TopicDto.builder()
				.id(createTopic.getId())
				.type(TEST_TOPIC_TYPE)
				.name(createTopic.getName())
				.image("UPDATE_IMAGE")
				.build();
		topicService.updateTopic(updateTopicDto);

		Topic updatedCorp = topicService.getTopic(updateTopicDto);
		assertThat(updatedCorp.getImage()).isEqualTo("UPDATE_IMAGE");
	}

	@Test(expected = TopicNotFoundException.class)
	@Transactional
	public void 수정하려는_토픽ID가_유효하지_않는_경우_예외를_반환한다() {
		Long invalidID = 123L;
		TopicDto updateTopicDto = TopicDto.builder()
				.id(invalidID)
				.type(TEST_TOPIC_TYPE)
				.name("UPDATE_NAME")
				.image("UPDATE_IMAGE")
				.build();

		topicService.updateTopic(updateTopicDto);
	}
}