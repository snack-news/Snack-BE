package com.snack.news.repository;

import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
	List<Topic> findAllByTypeIs(TopicType type);

	List<Topic> findByIdIn(List<Long> ids);

	boolean existsNotByName(String name);

	Topic findByName(String name);
}

