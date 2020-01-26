package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.RequestNewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.dto.Wrapper;
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

	@GetMapping
	public ResponseEntity<Wrapper<List<News>>> getNewsList(@ModelAttribute @Valid RequestNewsDto newsDto) {
		ListCursorResult<News> result = newsService.getNewsList(newsDto);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(result);
	}

	@GetMapping("/{newsId}")
	public ResponseEntity<Wrapper<News>> getNews(@PathVariable Long newsId) {
		return WrappedResponse.ok(newsService.getNews(newsId));
	}
}