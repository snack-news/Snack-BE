package com.snack.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snack.news.exception.SlackAuthorizationException;
import com.snack.news.service.SlackBotService;
import com.snack.news.util.SlackAuthHttpClient;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}