package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.RequestNewsDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepositoryCustom {
	List<News> findByNewsDto(RequestNewsDto requestNewsDto);

	List<News> findByNewsDto(RequestNewsDto requestNewsDto, LocalDateTime now);
}
