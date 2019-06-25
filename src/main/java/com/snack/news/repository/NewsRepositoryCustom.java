package com.snack.news.repository;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;

import java.util.List;

public interface NewsRepositoryCustom {
	List<News> findByNewsDto(NewsDto newsDto);
}
