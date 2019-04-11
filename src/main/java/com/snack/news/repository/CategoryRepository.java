package com.snack.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snack.news.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
