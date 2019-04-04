package com.snack.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snack.news.domain.Corporation;

public interface CorpRepository extends JpaRepository<Corporation, Long> {
}
