package com.snack.news.controller;

import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.service.TopicService;
import com.snack.news.strategy.TopicSorting;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	private final TopicService topicService;

	// todo : Domain이 직접 노출되는 문제
	@PostMapping
	public Topic createTopic(@RequestBody TopicDto topicDto) {
		return topicService.createTopic(topicDto);
	}

	@GetMapping
	public List<Topic> getTopicList(@RequestParam(defaultValue = "NAME") TopicSorting sort) {
		return topicService.getTopicList(sort);
	}

	@GetMapping("/{type}")
	public List<Topic> getTopicList(@PathVariable TopicType type, @RequestParam(defaultValue = "NAME") TopicSorting sort) {
		return topicService.getTypeTopicList(type, sort);
	}

	@PutMapping
	public Topic updateTopic(@RequestBody TopicDto topicDto) {
		return topicService.updateTopic(topicDto);
	}
}