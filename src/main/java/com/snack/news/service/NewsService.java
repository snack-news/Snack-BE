package com.snack.news.service;

import com.snack.news.domain.news.News;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.Period;
import com.snack.news.dto.RequestNewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;

	public ListCursorResult<News> getNewsList(RequestNewsDto newsDto) {
		new Period(newsDto.getStartDateTime(), newsDto.getEndDateTime()).validationCheck();

		if (Objects.nonNull(newsDto.getLastNewsId())) {
			newsDto.setEndDateTime(newsRepository.findById(newsDto.getLastNewsId()).orElseThrow(NewsNotFoundException::new).getPublishAt());
		}

		List<News> newsList = newsRepository.findByNewsDto(newsDto);
		return new ListCursorResult<>(newsList, hasNext(newsList, newsDto));
	}

	private boolean hasNext(List<News> list, RequestNewsDto newsDto) {
		if(list.isEmpty() || list.size() < newsDto.getLimitSize()) {
			return false;
		}
		return newsRepository.existsByPublishAtBefore(getLastElementInList(list).getPublishAt());
	}

	private <E> E getLastElementInList(List<E> list) {
		return list.get(list.size() - 1);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}
