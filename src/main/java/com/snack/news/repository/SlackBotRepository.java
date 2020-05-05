package com.snack.news.repository;

import com.snack.news.domain.slack.SlackChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlackBotRepository extends JpaRepository<SlackChannel, Long> {
}