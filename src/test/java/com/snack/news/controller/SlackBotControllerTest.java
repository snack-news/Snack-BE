package com.snack.news.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.snack.news.exception.SlackAuthorizationException;
import com.snack.news.exception.advice.ControllerExceptionHandler;
import com.snack.news.service.SlackBotService;
import com.snack.news.util.SlackAuthHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.snack.news.controller.ApiUrl.Domain.SLACK_AUTHORIZE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SlackBotControllerTest {
	@InjectMocks
	private SlackBotController slackBotController;

	@Mock
	private SlackBotService slackBotService;

	@Mock
	private SlackAuthHttpClient.Response slackAuthHttpClientResponse;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(slackBotController)
				.setControllerAdvice(new ControllerExceptionHandler())
				.addFilter(new CharacterEncodingFilter(UTF_8.toString(), true))
				.build();
	}

	@Test
	@DisplayName("Slack 인증 과정이 성공하면 OK 상태코드로 응답한다")
	void slackAuthorizeTest() throws Exception {
		when(slackBotService.authorize(anyString())).thenReturn(slackAuthHttpClientResponse);
		when(slackAuthHttpClientResponse.isOk()).thenReturn(true);

		mockMvc.perform(get(ApiUrl.builder().get(SLACK_AUTHORIZE).parm("code", anyString()).build())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Slack 인증에 실패하면 INTERNER_SERVER_ERROR 코드와 에러 메시지로 응답한다")
	void slackAuthorizeFailTest() throws Exception {
		when(slackBotService.authorize(anyString())).thenReturn(slackAuthHttpClientResponse);
		when(slackAuthHttpClientResponse.isOk()).thenThrow(SlackAuthorizationException.class);

		mockMvc.perform(get(ApiUrl.builder().get(SLACK_AUTHORIZE).parm("code", anyString()).build())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString(SlackAuthorizationException.errorMessage)))
				.andExpect(status().isInternalServerError());
	}

	@Test
	@DisplayName("Slack 인증 과정에서 json response 매핑에 실패하면 INTERNAL_SERVER_ERROR 코드와 에러 메시지로 응답한다")
	void slackResponseMappingFailTest() throws Exception {
		doThrow(JsonProcessingException.class).when(slackBotService).authorize(anyString());

		mockMvc.perform(get(ApiUrl.builder().get(SLACK_AUTHORIZE).parm("code", anyString()).build())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(content().string(containsString("AUTHORIZATION_ERROR")))
				.andExpect(status().isInternalServerError());
	}
}
