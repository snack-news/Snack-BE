package com.snack.news.service;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.snack.news.domain.News;
import com.snack.news.repository.NewsRepository;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class NewsService {
	private final NewsRepository newsRepository;

	public List<News> getNews(LocalDateTime startDateTime, LocalDateTime endDateTime) {
		List<News> newsList = newsRepository.findAll();

		return newsList.stream()
				.filter(news -> news.getCreateTime().isAfter(startDateTime))
				.filter(news -> news.getCreateTime().isBefore(endDateTime))
				.collect(toList());
	}

	public Optional<News> getNews(Long id) {
		return newsRepository.findById(id);
	}

	public void createNews(News news) {
		newsRepository.save(news);
	}
}
