package com.snack.news.controller;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
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

	@GetMapping("/body")
	public ResponseEntity<List<News>> getNewsListRequestBody(@RequestBody NewsDto newsDto) {
		List<News> result = newsService.getNewsList(newsDto);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}


	@GetMapping
	public ResponseEntity<List<News>> getNewsListPathVariable(@RequestParam(required = false)
															  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime startDateTime,
															  @RequestParam(required = false)
															  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime endDateTime,
															  @RequestParam(required = false) Long categoryId,
															  @RequestParam(required = false) List<Long> topicIds,
															  @RequestParam(required = false) List<Long> tagIds) {

		NewsDto requestNewsDto = NewsDto.builder()
				.startDateTime(startDateTime)
				.endDateTime(endDateTime)
				.categoryId(categoryId)
				.topicIds(topicIds)
				.tagIds(tagIds)
				.build();

		return getNewsListRequestBody(requestNewsDto);
	}

	@GetMapping("/{newsId}")
	public ResponseEntity<News> getNews(@PathVariable Long newsId) {
		return WrappedResponse.ok(newsService.getNews(newsId));
	}
}

