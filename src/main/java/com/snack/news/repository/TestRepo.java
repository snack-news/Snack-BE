package com.snack.news.repository;

import com.snack.news.domain.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 * @author delf
 */
public interface TestRepo extends Repository<News, Long>, JpaSpecificationExecutor<News> {
}
