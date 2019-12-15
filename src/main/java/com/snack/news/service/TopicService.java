package com.snack.news.service;

import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicSorting;
import com.snack.news.domain.topic.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.exception.TopicNotFoundException;
import com.snack.news.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class TopicService {
	private static final TopicType DEFAULT_TOPIC_TYPE = TopicType.CORP;
	private final TopicRepository topicRepository;

	@Transactional
	public Topic createTopic(TopicDto topicDto) {
		Topic topic = topicDto.getTopicNewEntity();

		try {
			topicRepository.save(topic);
		} catch (DataIntegrityViolationException e) {
			throw new TopicNotFoundException(); // 토픽 이름이 중복. todo: 예외 관리하기
		}

		return topic; // todo : Success Response
	}

	public List<Topic> getTypeTopicList(TopicType topicType, TopicSorting sorting) {
		List<Topic> topics = topicRepository.findAllByTypeIs(topicType);

		return topics.stream()
				.sorted(sorting.getOperator())
				.collect(toList());
	}


	public List<Topic> getTopicList(List<String> topicNames) {
		if (Objects.isNull(topicNames)) {
			return Collections.emptyList();
		}

		List<Topic> result = new ArrayList<>();
		for (String name : topicNames) {
			result.add(createTopicIfAbsent(name));
		}

		return result;
	}

	private Topic createTopicIfAbsent(String topicName) {
		if (topicRepository.existsNotByName(topicName)) {
			TopicDto topic = TopicDto.builder().name(topicName).type(DEFAULT_TOPIC_TYPE).build();
			return createTopic(topic);
		}
		return topicRepository.findByName(topicName);
	}

	@Transactional
	public Topic updateTopic(TopicDto topicDto) {
		Topic topic = topicDto.getTopicUpdateEntity();
		topicRepository.findById(topic.getId()).orElseThrow(TopicNotFoundException::new);

		return topicRepository.save(topic);
	}
}
