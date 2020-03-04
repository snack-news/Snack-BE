package com.snack.news.exception.advice;

import com.snack.news.dto.ErrorResponse;
import com.snack.news.exception.TopicBadRequestException;
import com.snack.news.exception.base.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.*;

@ControllerAdvice("com.snack.news.controller")
public class ControllerExceptionHandler {
	/**
	 * @param e DTO에서 설정된 validation 예외
	 * @return 누락된 필수 인자를 포함한 ResponseEntity 객체
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(ConstraintViolationException e) {
		List<String> badParamNames = new ArrayList<>();
		Set<ConstraintViolation<?>> set = e.getConstraintViolations();
		set.stream().map(ConstraintViolation::getPropertyPath).map(Path::toString).forEach(badParamNames::add);

		return ResponseEntity.badRequest().body(String.format("Argument Error: %s", badParamNames));
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
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(e.getErrorCode(), e.getMessage()));
	}
}