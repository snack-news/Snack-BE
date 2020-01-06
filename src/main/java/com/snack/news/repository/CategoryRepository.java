package com.snack.news.repository;

import com.snack.news.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	boolean existsById(Long aLong);
}