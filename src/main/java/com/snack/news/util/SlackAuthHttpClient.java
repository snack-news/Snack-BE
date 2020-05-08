package com.snack.news.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack.news.domain.slack.SlackChannel;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class SlackAuthHttpClient {

	private static RestTemplate slackHttpClient;

	public SlackAuthHttpClient(RestTemplate slackHttpClient) {
		SlackAuthHttpClient.slackHttpClient = slackHttpClient;
	}

	public static String postRequest(String urlStr, MultiValueMap<String, String> parms) {
		return slackHttpClient.postForObject(urlStr, parms, String.class);
	}

	public static class Response {
		private Map<String, Object> map;
		private static ObjectMapper objectMapper = new ObjectMapper();

		public Response(String jsonString) throws JsonProcessingException {
			map = objectMapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
			});
		}

		public boolean isOk() {
			return map.get("ok").equals(true);
		}

		public String getErrorMessage() throws IllegalAccessException {
			if (isOk()) {
				throw new IllegalAccessException();
			}
			return map.get("error").toString();
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
