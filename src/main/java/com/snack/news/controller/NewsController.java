package com.snack.news.controller;

import com.snack.news.domain.News;
import com.snack.news.dto.ErrorResponse;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;

import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.base.BadRequestException;
import com.snack.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.ArrayList;
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
	public ResponseEntity getNewsList(@RequestBody NewsDto newsDto) {
		List<News> result = newsService.getNewsList(newsDto);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);

	}

	@GetMapping("/{newsId}")
	public ResponseEntity getNews(@PathVariable Long newsId) {
		return WrappedResponse.ok(newsService.getNews(newsId));
	}

	@ExceptionHandler(NewsNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNewsNotFoundException(NewsNotFoundException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getMessage()), HttpStatus.NOT_FOUND);
	}

	/**
	 * News 생성 시 필수 값이 입력되지 않았을 때, 발생하는 예외 핸들링
	 *
	 * @param e NewsDto에서 설정된 validation 예외
	 * @return 필수 값을 포함한 에러 응답 객체
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		List<String> badParamNames = new ArrayList<>();
		e.getBindingResult().getAllErrors()
				.stream()
				.map(ObjectError::getDefaultMessage)
				.forEach(badParamNames::add);

		StringBuilder result = new StringBuilder();
		badParamNames.forEach(v -> result.append("[").append(v).append("] "));

		ErrorResponse response = new ErrorResponse(BadRequestException.ERROR_CODE, String.format("%s 값이 필요합니다.", result));

		return ResponseEntity.badRequest().body(response);
	}
}

