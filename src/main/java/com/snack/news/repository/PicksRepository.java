package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicksRepository extends JpaRepository<Pick, Long> {
	Pick findFirstByOrderByIdDesc();
	Page<Pick> findByIdLessThanOrderByPublishAtDesc(long id, Pageable pageable);
}