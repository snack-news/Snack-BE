package com.snack.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snack.news.service.SlackBotService;
import com.snack.news.util.SlackAuthHttpClient;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/slackbot")
public class SlackBotController {
	private final SlackBotService slackBotService;

	@GetMapping("/auth")
	public ResponseEntity<String> installAuth(@RequestParam Map<String, String> body) {
		try {
			SlackAuthHttpClient.Response response = slackBotService.authorize(body.get("code"));
			if (!response.isOk()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Slack authorization error: " + response.getErrorMessage());
			}
			slackBotService.save(response.toEntity());
			return ResponseEntity.ok().build();

		} catch (JsonProcessingException | IllegalAccessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server error during authorization");
		}
	}

	@GetMapping("/webhook-list")
	public List<String> getSlackChannelWebhookUrlList() {
		return slackBotService.getSlackChannelWebhookUrlList();
	}
}