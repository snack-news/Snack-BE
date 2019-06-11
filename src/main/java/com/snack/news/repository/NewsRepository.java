package com.snack.news.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.snack.news.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import com.snack.news.domain.News;

public interface NewsRepository extends JpaRepository<News, Long> {
	// todo : 만약에 뉴스가 엄청 많아지게된다면? SQL Between 속도가 제대로 나올까? 다른 방법은 없을까?
	List<News> findByCreateAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

	List<News> findAllByTopicsContains(List<Topic> topics);
}
