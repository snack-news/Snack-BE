package com.snack.news.controller;

import com.snack.news.domain.Tag;
import com.snack.news.dto.TagDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tag")
public class TagController {

	private final TagService tagService;

	@PostMapping
	public ResponseEntity<Tag> createTag(@RequestBody TagDto tagDto) {
		return WrappedResponse.ok(tagService.createTag(tagDto));
	}

	@GetMapping
	public ResponseEntity<List<Tag>> getTopicList() {
		return WrappedResponse.ok(tagService.getAllTagList());
	}

	@PutMapping
	public Tag updateTopic(@RequestBody TagDto tagDto) {
		return tagService.updateTag(tagDto);
	}
}
