package com.snack.news.controller;

import com.snack.news.domain.News;
import com.snack.news.service.NewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

	public NewsController(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping
	public List<News> getNews(){

		return null;
	}
}
