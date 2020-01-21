package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

	@GetMapping
	public ResponseEntity<ListCursorResult<News>> getNewsList(@ModelAttribute NewsDto newsDto) {
		ListCursorResult<News> result = newsService.getNewsList(newsDto);
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