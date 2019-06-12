package com.snack.news.repository;

import com.snack.news.domain.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
	List<Topic> findAll();

	List<Topic> findAllById(List<Long> ids);
}