package com.snack.news.service;

import com.snack.news.domain.category.Category;
import com.snack.news.dto.CategoryDto;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.repository.CategoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

	@InjectMocks
	private CategoryService categoryService;

	@Mock
	private CategoryRepository categoryRepository;

	@Test
	@DisplayName("카테고리를 생성할 수 있다")
	void createCategoryTest() {
		CategoryDto categoryDto = CategoryDto.builder().title(anyString()).build();

		categoryService.createCategory(categoryDto);
		verify(categoryRepository).save(any(Category.class));
	}

	@Test
	@DisplayName("카테고리를 수정할 수 있다")
	void updateCategoryTest() {
		CategoryDto originCategoryDto = CategoryDto.builder().build();
		when(categoryRepository.existsById(any())).thenReturn(true);

		categoryService.updateCategory(originCategoryDto);
		verify(categoryRepository).save(originCategoryDto.getUpdateEntity());
	}

	@Test
	@DisplayName("카테고리 수정 시 Category id가 유효하지 않다면 예외를 반환한다")
	void updateCategoryTestWhenIllegalCategoryId() {
		long invalidId = 999L;
		CategoryDto invalidIdDto = CategoryDto.builder().id(invalidId).build();

		when(categoryRepository.existsById(anyLong())).thenReturn(false);
		assertThrows(CategoryNotFoundException.class, () -> categoryService.updateCategory(invalidIdDto));
	}
}
