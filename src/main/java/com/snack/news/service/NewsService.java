package com.snack.news.service;

import com.snack.news.domain.category.Category;
import com.snack.news.domain.news.News;
import com.snack.news.domain.tag.Tag;
import com.snack.news.domain.topic.Topic;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.Period;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;

	public List<News> getNewsList(NewsDto newsDto) {
		new Period(newsDto.getStartDateTime(), newsDto.getEndDateTime()).validationCheck();
		return newsRepository.findByNewsDto(newsDto);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}