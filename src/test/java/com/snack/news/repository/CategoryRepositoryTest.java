package com.snack.news.repository;

import com.snack.news.domain.Category;
import com.snack.news.exception.CategoryNotFoundException;
import com.snack.news.fixture.NewsFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest extends NewsFixture {

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	@Transactional
	public void ID에_해당하는_Category를_가져올_수_있다() {
		Category category = categoryRepository.findById(1L).orElseThrow(CategoryNotFoundException::new);

		assertThat(category.getTitle(), equalTo("IT"));
	}
}