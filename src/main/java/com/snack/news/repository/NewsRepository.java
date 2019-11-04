package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long>, NewsRepositoryCustom {
	@Override
	List<News> findByNewsDto(NewsDto newsDto);

	Page<News> findAll(Pageable newsDto);
}