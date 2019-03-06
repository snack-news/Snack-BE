package com.snack.news.service;

import com.snack.news.domain.News;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NewsService {
	private final NewsRepository newsRepository2;

	public List<News> getNews() {
		return newsRepository2.findAll();
	}

	public void createNews(News news) {
		newsRepository2.save(news);
	}
}
