package com.snack.news.service;

import com.snack.news.domain.Topic;
import com.snack.news.dto.TopicDto;
import com.snack.news.exception.TopicNotFoundException;
import com.snack.news.repository.TopicRepository;
import com.snack.news.strategy.TopicSorting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class TopicService {
	private final TopicRepository topicRepository;

	@Transactional
	public Topic createTopic(TopicDto topicDto) {
		Topic topic = topicDto.getTopicNewEntity();

		topicRepository.save(topic);

		return topic; // todo : Success Response
	}

	public List<Topic> getTopicList(TopicSorting sorting) {
		List<Topic> topics = topicRepository.findAll();

		return topics.stream()
				.sorted(sorting.getOperator())
				.collect(toList());
	}

	@Transactional
	public List<Topic> getTopicList(String[] topics) {
		if(Objects.isNull(topics)) {
			return Collections.emptyList();
		}

		List<Topic> topicList = new ArrayList<>();
		for (String topicName : topics) {
			Topic nextTopic = topicRepository.findByName(topicName);
			if (Objects.isNull(nextTopic)) {
				System.out.println(topicName);
				nextTopic = Topic.builder().name(topicName).build();
				topicRepository.save(nextTopic);
			}
			topicList.add(nextTopic);
		}

		return topicList;
	}

	@Transactional
	public Topic updateTopic(TopicDto topicDto) {
		Topic topic = topicDto.getTopicUpdateEntity();
		topicRepository.findById(topic.getId()).orElseThrow(TopicNotFoundException::new);

		return topicRepository.save(topic);
	}

	public Topic getTopic(TopicDto topicDto) {
		return topicRepository.findById(topicDto.getId()).orElseThrow(TopicNotFoundException::new);
	}
}
