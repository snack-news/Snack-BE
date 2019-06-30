package com.snack.news.controller;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

	@PostMapping
	public NewsDto createNews(@RequestBody NewsDto newsDto) {
		return newsService.createNews(newsDto);
	}

	@GetMapping
	public List<News> getNewsList(@RequestBody NewsDto newsDto) {
		System.out.println("@#@#" + newsDto);
		return newsService.getNewsList(newsDto);
	}

	@GetMapping("/{newsId}")
	public News getNews(@PathVariable Long newsId) {
		return newsService.getNews(newsId);
	}
}
