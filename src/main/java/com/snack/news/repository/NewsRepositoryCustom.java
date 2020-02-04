package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.RequestQueryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepositoryCustom {
	List<News> findByNewsDto(RequestQueryDto requestQueryDto);

	List<News> findByNewsDto(RequestQueryDto requestQueryDto, LocalDateTime now);
}