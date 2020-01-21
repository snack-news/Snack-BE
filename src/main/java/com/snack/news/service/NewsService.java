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

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;

	public ListCursorResult<News> getNewsList(RequestNewsDto newsDto) {
		new Period(newsDto.getStartDateTime(), newsDto.getEndDateTime()).validationCheck();
		List<News> newsList = newsRepository.findByNewsDto(newsDto);
		return new ListCursorResult<>(newsList, hasNext(newsList));
	}

	private boolean hasNext(List<News> list) {
		if (list.isEmpty()) {
			return false;
		}

		RequestNewsDto requestDto = RequestNewsDto.builder()
				.limitSize(1)
				.lastNewsId(getLastElementInList(list).getId())
				.build();

		return !newsRepository.findByNewsDto(requestDto).isEmpty();
	}

	private <E> E getLastElementInList(List<E> list) {
		return list.get(list.size() - 1);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}
