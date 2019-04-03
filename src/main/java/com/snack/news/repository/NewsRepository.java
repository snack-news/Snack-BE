package com.snack.news.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snack.news.domain.News;

public interface NewsRepository extends JpaRepository<News, Long> {
	public List<News> findByCreateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
