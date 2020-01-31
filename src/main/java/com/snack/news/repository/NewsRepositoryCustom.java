package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.RequestInquiryDto;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsRepositoryCustom {
	List<News> findByNewsDto(RequestInquiryDto requestInquiryDto);

	List<News> findByNewsDto(RequestInquiryDto requestInquiryDto, LocalDateTime now);
}
