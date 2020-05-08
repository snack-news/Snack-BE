package com.snack.news.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SlackHttpClientBuilder {
	private final static int READ_TIMEOUT = 5000;
	private final static int CONNECT_TIMEOUT = 3000;

	@Bean
	public RestTemplate slackHttpClient() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(READ_TIMEOUT);
		factory.setConnectTimeout(CONNECT_TIMEOUT);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		factory.setHttpClient(httpClient);
		return new RestTemplate(factory);
	}
}
