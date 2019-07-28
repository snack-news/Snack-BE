package com.snack.news.controller;

import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.TopicService;
import com.snack.news.strategy.TopicSorting;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	private final TopicService topicService;

	@PostMapping
	public ResponseEntity<Topic> createTopic(@Valid @RequestBody TopicDto topicDto) {
		return WrappedResponse.ok(topicService.createTopic(topicDto));
	}

	@GetMapping
	public ResponseEntity<List<Topic>> getTopicList(@RequestParam(defaultValue = "NAME") TopicSorting sort) {
		return WrappedResponse.ok(topicService.getTopicList(sort));
	}

	@GetMapping("/{type}")
	public ResponseEntity<List<Topic>> getTopicList(@PathVariable TopicType type, @RequestParam(defaultValue = "NAME") TopicSorting sort) {
		return WrappedResponse.ok(topicService.getTypeTopicList(type, sort));
	}

	@PutMapping
	public ResponseEntity<Topic> updateTopic(@RequestBody TopicDto topicDto) {
		return WrappedResponse.ok(topicService.updateTopic(topicDto));
	}
}