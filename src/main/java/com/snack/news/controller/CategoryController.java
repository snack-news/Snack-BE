package com.snack.news.controller;

import com.snack.news.domain.category.Category;
import com.snack.news.dto.CategoryDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.dto.Wrapper;
import com.snack.news.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private final CategoryService categoryService;

	@PostMapping
	public WrappedResponse<Category> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		return WrappedResponse.ok(Wrapper.valueOf(categoryService.createCategory(categoryDto)));
	}

	@GetMapping
	public WrappedResponse<List<Category>> getCategoryList() {
		return WrappedResponse.ok(Wrapper.valueOf(categoryService.getCategoryList()));
	}

	@PutMapping
	public WrappedResponse<Category> updateCategory(@RequestBody CategoryDto categoryDto) {
		return WrappedResponse.ok(Wrapper.valueOf(categoryService.updateCategory(categoryDto)));
	}
}
