package com.snack.news.controller;

import com.snack.news.domain.tag.Tag;
import com.snack.news.dto.TagDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.dto.Wrapper;
import com.snack.news.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tag")
public class TagController {

	private final TagService tagService;

	@PostMapping
	public ResponseEntity<Wrapper<Tag>> createTag(@Valid @RequestBody TagDto tagDto) {
		return WrappedResponse.ok(tagService.createTag(tagDto));
	}

	@GetMapping
	public ResponseEntity<Wrapper<List<Tag>>> getTopicList() {
		return WrappedResponse.ok(tagService.getAllTagList());
	}

	@PutMapping
	public ResponseEntity<Wrapper<Tag>> updateTopic(@RequestBody TagDto tagDto) {
		return WrappedResponse.ok(tagService.updateTag(tagDto));
	}
}