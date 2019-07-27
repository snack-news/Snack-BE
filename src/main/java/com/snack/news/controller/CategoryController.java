package com.snack.news.controller;

import com.snack.news.domain.Category;
import com.snack.news.dto.CategoryDto;
import com.snack.news.dto.ErrorResponse;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.exception.CategoryBadRequestException;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private final CategoryService categoryService;

	@PostMapping
	public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return WrappedResponse.ok(categoryService.createCategory(categoryDto));
	}

	@GetMapping
	public ResponseEntity<List<Category>> getCategoryList() {
		return WrappedResponse.ok(categoryService.getCategoryList());
	}

	@PutMapping
	public ResponseEntity<Category> updateCategory(@RequestBody CategoryDto categoryDto) {
		return WrappedResponse.ok(categoryService.updateCategory(categoryDto));
	}

	@ExceptionHandler(CategoryNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNewsNotFoundException(CategoryNotFoundException e) {
		return new ResponseEntity<>(new ErrorResponse(e.getErrorCode(), e.getMessage()), HttpStatus.NOT_FOUND);
	}

	/**
	 * Category 생성 시 필수 값이 입력되지 않았을 때, 발생하는 예외 핸들링
	 *
	 * @param e CategoryDto에서 설정된 validation 예외
	 * @return 필수 값을 포함한 에러 응답 객체
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

		String badParamName = Objects.requireNonNull(e.getBindingResult().getAllErrors().get(0).getDefaultMessage()); // TODO: 수정 필요
		ErrorResponse response = new ErrorResponse(CategoryBadRequestException.ERROR_CODE, String.format("%s 값이 필요합니다.", badParamName));

		return ResponseEntity.badRequest().body(response);
	}
}