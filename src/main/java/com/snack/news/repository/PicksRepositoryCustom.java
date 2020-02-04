package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.RequestQueryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PicksRepositoryCustom {
	List<Pick> findByPickDto(RequestQueryDto requestPickDto);

	List<Pick> findByPickDto(RequestQueryDto requestPickDto, LocalDateTime now);
}
