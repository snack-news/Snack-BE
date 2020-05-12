package com.snack.news.service;

import com.snack.news.domain.category.Category;
import com.snack.news.dto.CategoryDto;
import com.snack.news.exception.CategoryBadRequestException;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryService {
	private final CategoryRepository categoryRepository;

	@Transactional
	public Category createCategory(CategoryDto categoryDto) {
		Category category = categoryDto.getNewEntity();
		return categoryRepository.save(category);
	}

	public Category getCategory(Long id) {
		return categoryRepository.findById(
				Optional.ofNullable(id).orElseThrow(CategoryBadRequestException::new)
		).orElseThrow(CategoryNotFoundException::new);
	}

	@Transactional(readOnly = true)
	public List<Category> getCategoryList() {
		return categoryRepository.findAll();
	}

	@Transactional
	public Category updateCategory(CategoryDto categoryDto) {
		if (!categoryRepository.existsById(categoryDto.getId())) {
			throw new CategoryNotFoundException();
		}
		return categoryRepository.save(categoryDto.getUpdateEntity());
	}
}