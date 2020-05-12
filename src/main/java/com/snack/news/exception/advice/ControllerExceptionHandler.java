package com.snack.news.exception.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snack.news.dto.ErrorResponse;
import com.snack.news.exception.SlackAuthorizationException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@ControllerAdvice("com.snack.news.controller")
public class ControllerExceptionHandler {
	/**
	 * @param e DTO에서 설정된 validation 예외
	 * @return 누락된 필수 인자를 포함한 ResponseEntity 객체
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(ConstraintViolationException e) {
		List<String> badParamNames = new ArrayList<>();
		Set<ConstraintViolation<?>> set = e.getConstraintViolations();
		set.stream().map(ConstraintViolation::getPropertyPath).map(Path::toString).forEach(badParamNames::add);

		return ResponseEntity.badRequest().body(ErrorResponse.builder()
				.exceptionCode(NotFoundException.ERROR_CODE)
				.exceptionMessage("부적절한 요청")
				.detailMessage(String.format("Argument Error: %s", badParamNames))
				.build());
	}

	/**
	 * @param e 잘못된 enum 생성 시도로 인한 예외
	 * @return 잘못된 파라미터의 이름과 그 값을 포함한 ResponseEntity 객체
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		String badParamName = e.getParameter().getParameterName();
		String badValue = Objects.requireNonNull(e.getValue()).toString();

		TopicBadRequestException topicBadRequestException = new TopicBadRequestException(badParamName, badValue);
		ErrorResponse response = ErrorResponse.builder()
				.exceptionCode(topicBadRequestException.getErrorCode())
				.exceptionMessage(topicBadRequestException.getMessage())
				.detailMessage(topicBadRequestException.getDetailMessage())
				.build();

		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNewsNotFoundException(NotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.builder()
				.exceptionCode(e.getErrorCode())
				.exceptionMessage(e.getMessage())
				.detailMessage("")
				.build());
	}

	@ExceptionHandler(JsonProcessingException.class)
	public ResponseEntity<ErrorResponse> handleJsonProcessingExceptionException() {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
				.exceptionCode("AUTHORIZATION_ERROR")
				.detailMessage("Authorization server error")
				.detailMessage("Response json mapping failed")
				.build());
	}

	@ExceptionHandler(SlackAuthorizationException.class)
	public ResponseEntity<ErrorResponse> handleSlackAuthorizeException(SlackAuthorizationException e) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.builder()
				.exceptionCode(e.getErrorCode())
				.exceptionMessage(e.getMessage())
				.detailMessage(e.getDetailMessage())
				.build());
	}
}