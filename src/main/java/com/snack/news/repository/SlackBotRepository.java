package com.snack.news.repository;

import com.snack.news.domain.slack.SlackChannel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Iterator;
import java.util.List;

public interface SlackBotRepository extends JpaRepository<SlackChannel, Long> {
	void deleteByTeamId(String teamId);

	void deleteInBatchByWebhookUrl(String webhookUrl);
}