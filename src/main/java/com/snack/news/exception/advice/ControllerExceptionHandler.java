package com.snack.news.exception.advice;

import com.snack.news.dto.ErrorResponse;
import com.snack.news.exception.TopicBadRequestException;
import com.snack.news.exception.base.BadRequestException;
import com.snack.news.exception.base.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ControllerAdvice("com.snack.news.controller")
public class ControllerExceptionHandler {
	/**
	 * @param e DTO에서 설정된 validation 예외
	 * @return 누락된 필수 인자을 포함한 ResponseEntity 객체
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

		ErrorResponse response = new ErrorResponse(BadRequestException.ERROR_CODE, String.format("%s값이 필요합니다.", result));

		return ResponseEntity.badRequest().body(response);
	}

	/**
	 * @param e 잘못된 enum 생성 시도로 인한 예외
	 * @return 잘못된 파라미터의 이름과 그 값을 포함한 ResponseEntity 객체
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		TopicBadRequestException topicBadRequestException = new TopicBadRequestException();

		String badParamName = e.getParameter().getParameterName();
		String badValue = Objects.requireNonNull(e.getValue()).toString();
		ErrorResponse response = new ErrorResponse(topicBadRequestException.getErrorCode(), topicBadRequestException.getMessage(badParamName, badValue));

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNewsNotFoundException(NotFoundException e) {
		return ResponseEntity.notFound().build();
	}
}