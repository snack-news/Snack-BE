package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.RequestInquiryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface PicksRepositoryCustom {
	List<Pick> findByPickDto(RequestInquiryDto requestPickDto);

	List<Pick> findByPickDto(RequestInquiryDto requestPickDto, LocalDateTime now);
}
