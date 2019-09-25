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
@RequestMapping("admin/api")
public class AdminController {

	private final NewsService newsService;

	@GetMapping("/news")
	public ResponseEntity<Page<News>> getNewsList() {
		return getNewsList(1);
	}

	@GetMapping("/news/{page}")
	public ResponseEntity<Page<News>> getNewsList(@PathVariable int page) {
		Page<News> result = newsService.getNewsListForAdmin(page);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}

	@PostMapping("/news")
	public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto) {
		return WrappedResponse.ok(newsService.createNews(newsDto));
	}
}
