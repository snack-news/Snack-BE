package com.snack.news.repository;

import com.snack.news.domain.Category;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.fixture.NewsTestcase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest extends NewsTestcase {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	@Transactional
	public void ID에_해당하는_Category를_가져올_수_있다() {
		Category category = categoryRepository.findById(1L).orElseThrow(CategoryNotFoundException::new);

		assertThat(category.getTitle()).isEqualTo("IT");
	}
}