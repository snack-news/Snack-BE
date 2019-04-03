package com.snack.news.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.service.NewsService;

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
	public List<News> getNews(@ModelAttribute NewsDto newsDto) {
		return newsService.getNewsList(newsDto);
	}

	@GetMapping("/{newsId}")
	public News getNews(@PathVariable Long newsId) {
		return newsService.getNews(newsId);
	}
}
