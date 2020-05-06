package com.snack.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack.news.domain.slack.SlackChannel;
import com.snack.news.repository.SlackBotRepository;
import com.snack.news.util.SlackAuthHttpClient;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties("slackbot")
@Setter
public class SlackBotService {
	private final SlackBotRepository slackBotRepository;
	private final String SLACK_AUTH_URL = "https://slack.com/api/oauth.v2.access";

	private String clientId;
	private String clientSecret;

	public SlackBotService(SlackBotRepository slackBotRepository) {
		this.slackBotRepository = slackBotRepository;
	}

	public List<String> getSlackChannelWebhookUrlList() {
		return slackBotRepository.findAll().stream()
				.map(SlackChannel::getWebhookUrl)
				.collect(Collectors.toList());
	}

	public SlackAuthResponse authorize(String code) throws JsonProcessingException {
		String jsonResponse = SlackAuthHttpClient.postRequest(SLACK_AUTH_URL, generateAuthBody(code));
		return new SlackAuthResponse(jsonResponse);
	}

	@Transactional
	public void save(SlackChannel channelInfo) {
		slackBotRepository.save(channelInfo);
	}

	private Map<String, String> generateAuthBody(String code) {
		Map<String, String> body = new HashMap<>();

		body.put("client_id", clientId);
		body.put("client_secret", clientSecret);
		body.put("code", code);

		return body;
	}

	public static class SlackAuthResponse {
		private Map<String, Object> map;

		private final String CLIENT_ID = "client_id";
		private final String CLIENT_SECRET = "client_secret";

		public SlackAuthResponse(String jsonString) throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
			map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {});
		}

		public boolean isOk() {
			return map.get("ok").equals(true);
		}

		public SlackChannel toEntity() {
			return SlackChannel.builder()
					.accessToken(map.get("access_token").toString())
					.authedUserId(((Map) map.get("authed_user")).get("id").toString())
					.botUserId(map.get("bot_user_id").toString())
					.teamId(((Map) map.get("team")).get("id").toString())
					.teamName(((Map) map.get("team")).get("name").toString())
					.channelId(((Map) map.get("incoming_webhook")).get("channel_id").toString())
					.webhookUrl(((Map) map.get("incoming_webhook")).get("url").toString())
					.configurationUrl(((Map) map.get("incoming_webhook")).get("configuration_url").toString())
					.build();
		}
	}
}