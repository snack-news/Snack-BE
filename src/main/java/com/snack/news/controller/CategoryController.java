package com.snack.news.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snack.news.domain.Category;
import com.snack.news.dto.CategoryDto;
import com.snack.news.service.CategoryService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
	private final CategoryService categoryService;

	// todo : Domain이 직접 노출되는 문제
	@PostMapping
	public Category createCorp(@RequestBody CategoryDto categoryDto) {
		return categoryService.createCategory(categoryDto);
	}

	@GetMapping
	public List<Category> getCategoryList() {
		return categoryService.getCategoryList();
	}

	@PutMapping
	public Category updateCategory(@RequestBody CategoryDto categoryDto) {
		return categoryService.updateCategory(categoryDto);
	}
}