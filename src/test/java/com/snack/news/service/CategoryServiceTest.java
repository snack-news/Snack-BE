package com.snack.news.service;

import com.snack.news.domain.Category;
import com.snack.news.dto.CategoryDto;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.repository.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.ThreadLocalRandom;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	@Transactional
	public void 카테고리를_생성할_수_있다() {
		final String testCategoryTitle = Long.toString(ThreadLocalRandom.current().nextLong());
		CategoryDto categoryDto = CategoryDto.builder().title(testCategoryTitle).build();
		Category savedCategory = categoryService.createCategory(categoryDto);

		Category expectedCategory = findCategoryByIdOrElseThrowException(savedCategory.getId());
		assertThat(expectedCategory.getTitle(), equalTo(testCategoryTitle));
	}

	@Test
	@Transactional
	public void 카테고리를_수정할_수_있다() {
		final String originTestCategoryTitle = Long.toString(ThreadLocalRandom.current().nextLong());
		CategoryDto originCategoryDto = CategoryDto.builder().title(originTestCategoryTitle).build();

		Category savedCategory = categoryRepository.save(originCategoryDto.getNewEntity());

		final String updatedTestCategoryTitle = Long.toString(ThreadLocalRandom.current().nextLong());
		CategoryDto updateCategoryDto = CategoryDto.builder().id(savedCategory.getId()).title(updatedTestCategoryTitle).build();

		categoryService.updateCategory(updateCategoryDto);
		Category expectedCategory = findCategoryByIdOrElseThrowException(savedCategory.getId());

		assertThat(expectedCategory.getTitle(), equalTo(updatedTestCategoryTitle));
	}

	private Category findCategoryByIdOrElseThrowException(long id) {
		return categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);
	}

	@Test(expected = CategoryNotFoundException.class)
	@Transactional
	public void 카테고리_ID가_유효하지_않다면_예외를_반환한다() {
		final long invalidId = ThreadLocalRandom.current().nextLong();
		CategoryDto invalidIdDto = CategoryDto.builder().id(invalidId).build();

		categoryService.updateCategory(invalidIdDto);
	}
}
