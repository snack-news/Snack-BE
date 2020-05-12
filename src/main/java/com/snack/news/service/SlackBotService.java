package com.snack.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snack.news.domain.slack.SlackChannel;
import com.snack.news.repository.SlackBotRepository;
import com.snack.news.util.SlackAuthHttpClient;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@ConfigurationProperties("slackbot")
@Setter
public class SlackBotService {
	private final SlackBotRepository slackBotRepository;
	private final SlackAuthHttpClient slackAuthHttpClient;

	public SlackBotService(SlackBotRepository slackBotRepository, SlackAuthHttpClient slackAuthHttpClient) {
		this.slackBotRepository = slackBotRepository;
		this.slackAuthHttpClient = slackAuthHttpClient;
	}

	private String slackAuthUrl;
	private String clientId;
	private String clientSecret;

	public List<String> getSlackChannelWebhookUrlList() {
		return slackBotRepository.findAll().stream()
				.map(SlackChannel::getWebhookUrl)
				.collect(Collectors.toList());
	}

	public SlackAuthHttpClient.Response authorize(String code) throws JsonProcessingException {
		String jsonResponse = slackAuthHttpClient.requestAuthorization(slackAuthUrl, generateAuthRequestBody(code));
		return new SlackAuthHttpClient.Response(jsonResponse);
	}

	@Transactional
	public void save(SlackChannel channelInfo) {
		slackBotRepository.save(channelInfo);
	}

	private MultiValueMap<String, String> generateAuthRequestBody(String code) {
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

		body.put("client_id", Collections.singletonList(clientId));
		body.put("client_secret", Collections.singletonList(clientSecret));
		body.put("code", Collections.singletonList(code));
		return body;
	}
}