package com.snack.news.service;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snack.news.domain.Category;
import com.snack.news.dto.CategoryDto;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.repository.CategoryRepository;

@Service
@AllArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public Category createCategory(CategoryDto categoryDto) {
		Category category = categoryDto.getNewEntity();

		return categoryRepository.save(category);
	}

	@Transactional(readOnly = true)
	public List<Category> getCategoryList() {
		return categoryRepository.findAll();
	}

	@Transactional
	public Category updateCategory(CategoryDto categoryDto) {
		Category category = categoryDto.getUpdateEntity();
		categoryRepository.findById(category.getId()).orElseThrow(CategoryNotFoundException::new);

		return categoryRepository.save(category);
	}
}
