package com.snack.news.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snack.news.domain.slack.SlackChannel;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class SlackAuthHttpClient {

	private final static int READ_TIMEOUT = 5000;
	private final static int CONNECT_TIMEOUT = 3000;

	private static HttpComponentsClientHttpRequestFactory factory;

	static {
		factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(READ_TIMEOUT);
		factory.setConnectTimeout(CONNECT_TIMEOUT);
	}

	public static String postRequest(String urlStr, MultiValueMap<String, String> parms) {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		factory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(factory);

		return restTemplate.postForObject(urlStr, parms, String.class);
	}

	public static class Response {
		private Map<String, Object> map;

		public Response(String jsonString) throws JsonProcessingException {
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
