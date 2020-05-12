package com.snack.news.service;

import com.snack.news.domain.news.News;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.Period;
import com.snack.news.dto.RequestQueryDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;
import com.snack.news.util.CollectionHelper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;

	public ListCursorResult<News> getNewsList(RequestQueryDto newsDto) {
		new Period(newsDto.getStartDateTime(), newsDto.getEndDateTime()).validationCheck();

		if (Objects.nonNull(newsDto.getLastId())) {
			newsDto.setEndDateTime(newsRepository.findById(newsDto.getLastId()).orElseThrow(NewsNotFoundException::new).getPublishAt());
		}

		List<News> newsList = newsRepository.findByNewsDto(newsDto);
		return new ListCursorResult<>(newsList, hasNext(newsList, newsDto));
	}

	private boolean hasNext(List<News> list, RequestQueryDto newsDto) {
		if(list.isEmpty() || list.size() < newsDto.getLimitSize()) {
			return false;
		}
		return newsRepository.existsByPublishAtBefore(CollectionHelper.getLastElementInList(list).getPublishAt());
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}
