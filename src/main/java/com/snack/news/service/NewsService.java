package com.snack.news.service;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;

@AllArgsConstructor
@Service
public class NewsService {
	private final NewsRepository newsRepository;

	@Transactional
	public NewsDto createNews(NewsDto newsDto) {
		News news = newsDto.toEntity();

		newsRepository.save(news);

		return NewsDto.builder().id(news.getId()).build();
	}

	public List<News> getNewsList(NewsDto newsDto) {
		LocalDateTime startTime = newsDto.getStartDateTime();
		LocalDateTime endTime = newsDto.getEndDateTime();

		return newsRepository.findByCreateAtBetween(startTime, endTime);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}
