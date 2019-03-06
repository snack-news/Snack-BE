package com.snack.news.service;

import com.snack.news.domain.News;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NewsService {
	private final NewsRepository newsRepository;

	public List<News> getNews() {
		return newsRepository.findAll();
	}

	public void createNews(News news) {
		newsRepository.save(news);
	}
}
