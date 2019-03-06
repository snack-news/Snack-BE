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

	@GetMapping
	public List<News> getNews() {
		return newsService.getNews();
	}

	@PostMapping
	public void createNews(@ModelAttribute NewsDto newsDto) {
		newsService.createNews(newsDto.toEntity());
	}
}
