package com.snack.news.controller;

import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicSorting;
import com.snack.news.domain.topic.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.dto.Wrapper;
import com.snack.news.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	private final TopicService topicService;

	@PostMapping
	public WrappedResponse<Topic> createTopic(@Valid @RequestBody TopicDto topicDto) {
		return WrappedResponse.ok(Wrapper.valueOf(topicService.createTopic(topicDto)));
	}

	@GetMapping("/{type}")
	public WrappedResponse<List<Topic>> getTopicList(@PathVariable TopicType type, @RequestParam(defaultValue = "NAME") TopicSorting sort) {
		return WrappedResponse.ok(Wrapper.valueOf(topicService.getTypeTopicList(type, sort)));
	}

	@PutMapping
	public WrappedResponse<Topic> updateTopic(@RequestBody TopicDto topicDto) {
		return WrappedResponse.ok(Wrapper.valueOf(topicService.updateTopic(topicDto)));
	}
}
