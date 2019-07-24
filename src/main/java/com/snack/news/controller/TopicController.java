package com.snack.news.controller;

import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.exception.TopicNotFoundException;
import com.snack.news.service.TopicService;
import com.snack.news.strategy.TopicSorting;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	private final TopicService topicService;

	// todo : Domain이 직접 노출되는 문제
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

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		// TODO: 에러 메시지 세분화
		return ResponseEntity.badRequest().body(new TopicNotFoundException().getMessage());
	}

	@PutMapping
	public ResponseEntity<Topic> updateTopic(@RequestBody TopicDto topicDto) {
		return WrappedResponse.ok(topicService.updateTopic(topicDto));
	}
}