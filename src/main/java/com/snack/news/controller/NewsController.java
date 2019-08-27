package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

	@PostMapping
	public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto) {
		return WrappedResponse.ok(newsService.createNews(newsDto));
	}

	@GetMapping
	public ResponseEntity<List<News>> getNewsListRequestBody(@ModelAttribute NewsDto newsDto) {
		List<News> result = newsService.getNewsList(newsDto);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}

	@GetMapping("/{newsId}")
	public ResponseEntity<News> getNews(@PathVariable Long newsId) {
		return WrappedResponse.ok(newsService.getNews(newsId));
	}
}