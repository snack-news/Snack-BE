package com.snack.news.controller;

import com.snack.news.domain.Tag;
import com.snack.news.dto.ErrorResponse;
import com.snack.news.dto.TagDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.exception.TagBadRequestException;
import com.snack.news.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tag")
public class TagController {

	private final TagService tagService;

	@PostMapping
	public ResponseEntity<Tag> createTag(@Valid @RequestBody TagDto tagDto) {
		return WrappedResponse.ok(tagService.createTag(tagDto));
	}

	@GetMapping
	public ResponseEntity<List<Tag>> getTopicList() {
		return WrappedResponse.ok(tagService.getAllTagList());
	}

	@PutMapping
	public Tag updateTopic(@RequestBody TagDto tagDto) {
		return tagService.updateTag(tagDto);
	}

	/**
	 * Tag 생성 시 필수 값이 입력되지 않았을 때, 발생하는 예외 핸들링
	 *
	 * @param e TagDto에서 설정된 validation 예외
	 * @return 필수 값을 포함한 에러 응답 객체
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		String badParamName = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()); // TODO: 수정 필요
		ErrorResponse response = new ErrorResponse(TagBadRequestException.ERROR_CODE, String.format("%s 값이 필요합니다.", badParamName));

		return ResponseEntity.badRequest().body(response);
	}
}
