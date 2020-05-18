package com.snack.news.repository;

import com.snack.news.domain.slack.SlackChannel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SlackBotRepository extends JpaRepository<SlackChannel, Long> {
	void deleteByTeamId(String teamId);

	void deleteByWebhookUrl(String webhookUrl);

	default void deleteAllByWebhookUrl(Iterable<String> webhookUrls) {
		webhookUrls.forEach(this::deleteByWebhookUrl);
	}
}