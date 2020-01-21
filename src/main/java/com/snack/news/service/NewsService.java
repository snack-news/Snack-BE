package com.snack.news.service;

import com.snack.news.domain.news.News;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.Period;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;

	public ListCursorResult<News> getNewsList(NewsDto newsDto) {
		new Period(newsDto.getStartDateTime(), newsDto.getEndDateTime()).validationCheck();
		List<News> newsList = newsRepository.findByNewsDto(newsDto);
		return new ListCursorResult<>(newsList, hasNext(newsList, newsDto));
	}

	private boolean hasNext(List<News> list, NewsDto newsDto) {
		newsDto.setLimitSize(1);
		newsDto.setId(getLastElementInList(list).getId());

		return !newsRepository.findByNewsDto(newsDto).isEmpty();
	}

	private <E> E getLastElementInList(List<E> list) {
		if(list == null) throw new IllegalArgumentException();
		if(list.isEmpty()) throw new IllegalArgumentException();

		return list.get(list.size() - 1);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}
