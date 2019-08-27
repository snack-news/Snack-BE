package com.snack.news.repository;

import com.snack.news.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
	List<Tag> findByIdIn(List<Long> ids);
}
