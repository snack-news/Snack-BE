package com.snack.news.controller;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.exception.NewsBadRequestException;
import com.snack.news.service.NewsService;
import com.snack.news.util.WeekUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

	@PostMapping
	public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto) {
		return WrappedResponse.ok(newsService.createNews(newsDto));
	}

	@GetMapping
	public ResponseEntity<List<News>> getNewsListRequestBody(@ModelAttribute NewsDto newsDto) {
		boolean isValidDate = isBothDatesInOneWeekWithSameMonth(newsDto.getStartDateTime(), newsDto.getEndDateTime());
		if (!isValidDate) {
			throw new NewsBadRequestException();
		}

		List<News> result = newsService.getNewsList(newsDto);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}

	@GetMapping("/{newsId}")
	public ResponseEntity<News> getNews(@PathVariable Long newsId) {
		return WrappedResponse.ok(newsService.getNews(newsId));
	}

	private boolean isBothDatesInOneWeekWithSameMonth(LocalDateTime start, LocalDateTime end) {
		return WeekUtil.isBothDatesInOneWeekWithSameMonth(start, end);
	}
}