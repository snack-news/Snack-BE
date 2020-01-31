package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.RequestNewsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long>, NewsRepositoryCustom {
	@Override
	List<News> findByNewsDto(RequestNewsDto requestNewsDto);

	Page<News> findAll(Pageable newsDto);

	boolean existsByPublishAtBefore(LocalDateTime publishAt);
}