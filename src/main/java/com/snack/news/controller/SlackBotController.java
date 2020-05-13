package com.snack.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snack.news.exception.SlackAuthorizationException;
import com.snack.news.service.SlackBotService;
import com.snack.news.util.SlackAuthHttpClient;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/slackbot")
public class SlackBotController {
	private final SlackBotService slackBotService;

	@GetMapping("/auth")
	public ResponseEntity<String> installAuth(@RequestParam String code) throws JsonProcessingException, IllegalAccessException {
		SlackAuthHttpClient.Response response = slackBotService.authorize(code);
		if (!response.isOk()) {
			throw new SlackAuthorizationException(response.getErrorMessage());
		}

		slackBotService.save(response.toEntity());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/webhook-list")
	public List<String> getSlackChannelWebhookUrlList() {
		return slackBotService.getSlackChannelWebhookUrlList();
	}

	@SuppressWarnings("rawtypes")
	@PostMapping("/event")
	public void handleEvent(@RequestBody Map<String, Object> body) throws UnexpectedException {
		Map eventInfo = (Map) Optional.ofNullable(body.get("event")).orElse(body);
		String type = eventInfo.get("type").toString();

		switch (type) {
			case "app_uninstalled":
				slackBotService.deleteSlackChannelByTeamId(body.get("team_id").toString());
				break;
			case "channel_deleted":
				slackBotService.deleteSlackChannelByChannelId(body.get("channel").toString());
				break;
		}

		throw new UnexpectedException("Error occurred");
	}
}