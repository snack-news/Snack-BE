package com.snack.news.controller;

import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import com.snack.news.dto.ErrorResponse;
import com.snack.news.dto.TopicDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.exception.TopicBadRequestException;
import com.snack.news.service.TopicService;
import com.snack.news.strategy.TopicSorting;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	private final TopicService topicService;

	@PostMapping
	public ResponseEntity<Topic> createTopic(@Valid @RequestBody TopicDto topicDto) {
		return WrappedResponse.ok(topicService.createTopic(topicDto));
	}

	@GetMapping
	public ResponseEntity<List<Topic>> getTopicList(@RequestParam(defaultValue = "NAME") TopicSorting sort) {
		return WrappedResponse.ok(topicService.getTopicList(sort));
	}

	@GetMapping("/{type}")
	public ResponseEntity<List<Topic>> getTopicList(@PathVariable TopicType type, @RequestParam(defaultValue = "NAME") TopicSorting sort) {
		return WrappedResponse.ok(topicService.getTypeTopicList(type, sort));
	}

	@PutMapping
	public ResponseEntity<Topic> updateTopic(@RequestBody TopicDto topicDto) {
		return WrappedResponse.ok(topicService.updateTopic(topicDto));
	}

	/**
	 * 컨트롤러에서 TopicType과 TopicSorting 값을 받을 때 지정된 enum 타입 이외의 것으로 생성을 시도했을 때 발생하는 예외를 핸들링
	 *
	 * @param e 잘못된 enum 생성 시도로 인한 예외
	 * @return 잘못된 파라미터의 이름과 그 값을 포함한 에러 응답객체
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		TopicBadRequestException topicBadRequestException = new TopicBadRequestException();

		String badParamName = e.getParameter().getParameterName();
		String badValue = Objects.requireNonNull(e.getValue()).toString();
		ErrorResponse response = new ErrorResponse(topicBadRequestException.getErrorCode(), topicBadRequestException.getMessage(badParamName, badValue));

		return ResponseEntity.badRequest().body(response);
	}

	/**
	 * Topic 생성 시 필수 값이 입력되지 않았을 때, 발생하는 예외 핸들링
	 *
	 * @param e TopicDto에서 설정된 validation 예외
	 * @return 필수 값을 포함한 에러 응답 객체
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String badParamName = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()); // TODO: 수정 필요
		ErrorResponse response = new ErrorResponse(TopicBadRequestException.ERROR_CODE, String.format("%s 값이 필요합니다.", badParamName));

		return ResponseEntity.badRequest().body(response);
	}
}