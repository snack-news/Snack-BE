package com.snack.news.controller;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping
	public List<News> getNews(@ModelAttribute NewsDto newsDto) {
		return newsService.getNews(newsDto.getStartDateTime(), newsDto.getEndDateTime());
	}

	@GetMapping("/id")
	public Optional<News> getNews(@PathVariable Long id){
		return newsService.getNews(id);
	}

	@PostMapping
	public void createNews(@ModelAttribute NewsDto newsDto) {
		newsService.createNews(newsDto.toEntity());
	}
}
