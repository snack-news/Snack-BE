package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	private final NewsService newsService;

	@PostMapping
	public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto) {
		return WrappedResponse.ok(newsService.createNews(newsDto));
	}

	@GetMapping({"/news", "/news/{page}"})
	public ResponseEntity<Page<News>> getNewsList(@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
												  @PathVariable Optional<Integer> page) {
		Page<News> result = newsService.getNewsListForAdmin(page.orElse(0));
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		result.getSize();
		return WrappedResponse.ok(result);
	}
}