package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.RequestQueryDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PicksRepository extends JpaRepository<Pick, Long>, PicksRepositoryCustom {

	@Override
	List<Pick> findByPickDto(RequestQueryDto requestPickDto);

	boolean existsByPublishAtBefore(LocalDateTime publishAt);
}