package com.snack.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snack.news.exception.SlackAuthorizationException;
import com.snack.news.service.SlackBotService;
import com.snack.news.util.SlackAuthHttpClient;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

	@PostMapping("/event")
	public void handleEvent(@RequestBody Map<String, Object> body) throws IllegalAccessException {
		try {
			slackBotService.deleteSlackChannelByTeamId(body.get("team_id").toString());
		} catch (NullPointerException e) {
			throw new IllegalAccessException("Slack event subscribe body mapping error");
		}
	}

	@DeleteMapping
	public void deleteChannel(List<String> webhookUrlList) {
		slackBotService.deleteInvalidSlackChannel(webhookUrlList);
	}
}