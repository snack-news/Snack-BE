package com.snack.news.controller;

import com.snack.news.domain.Tag;
import com.snack.news.dto.TagDto;
import com.snack.news.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tag")
public class TagController {

	private final TagService tagService;

	@PostMapping
	public Tag createTag(@RequestBody TagDto tagDto) {
		return tagService.createTag(tagDto);
	}

	@GetMapping
	public List<Tag> getTopicList() {
		return tagService.getAllTagList();
	}

	@PutMapping
	public Tag updateTopic(@RequestBody TagDto tagDto) {
		return tagService.updateTag(tagDto);
	}
}
