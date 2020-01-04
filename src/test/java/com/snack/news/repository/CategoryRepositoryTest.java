package com.snack.news.repository;

import com.snack.news.domain.category.Category;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.fixture.NewsFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class CategoryRepositoryTest extends NewsFixture {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	@DisplayName("ID에 해당하는 Category를 가져올 수 있다")
	@Transactional
	void getCategoryById() {
		Category category = categoryRepository.findById(1L).orElseThrow(CategoryNotFoundException::new);

		assertThat(category.getTitle()).isEqualTo("IT");
	}
}