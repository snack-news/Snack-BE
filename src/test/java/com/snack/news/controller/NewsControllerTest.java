package com.snack.news.controller;


import com.snack.news.domain.news.News;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.RequestNewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.advice.ControllerExceptionHandler;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.service.NewsService;
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
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class NewsControllerTest extends NewsFixture {

	@InjectMocks
	private NewsController newsController;

	private final static String NEWS_API_URL = "/api/news";

	@Mock
	private NewsService newsService;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(newsController)
				.setHandlerExceptionResolvers(createExceptionResolver())
				.build();
	}

	private ExceptionHandlerExceptionResolver createExceptionResolver() {
		ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
			protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
				Method method = new ExceptionHandlerMethodResolver(ControllerExceptionHandler.class).resolveMethod(exception);
				return new ServletInvocableHandlerMethod(new ControllerExceptionHandler(), Objects.requireNonNull(method));
			}
		};
		exceptionResolver.afterPropertiesSet();
		return exceptionResolver;
	}


	@Test
	@DisplayName("뉴스 조회 요청이 정상적으로 이루어진다")
	void requestCreateNewsTest() throws Exception {
		mockMvc.perform(get(NEWS_API_URL + "/" + TEST_SOME_ID_LONG)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 조회 요청이 ID가 부적절하다면 NOTFOUND 상태코드로 응답한다")
	void requestCreateNewsTestWithInvalidNewsId() throws Exception {
		when(newsService.getNews(anyLong())).thenThrow(NewsNotFoundException.class);

		mockMvc.perform(get(NEWS_API_URL + "/" + anyLong())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("뉴스 리스트 조회 요청시 값이 있다면 OK 상태코드로 응답한다")
	void requestNewsListTest() throws Exception {

		when(newsService.getNewsList(any(RequestNewsDto.class))).thenReturn(mockNewsResult);

		final String necessaryQueryString = "?startDateTime=2019-07-01T00:00";
		mockMvc.perform(get(NEWS_API_URL + necessaryQueryString))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 리스트 조회 요청시 값이 없다면 NOCONTENT 상태코드로 응답한다")
	void requestNewsListTestWhenNoneResult() throws Exception {
		ListCursorResult<News> emptyResult = new ListCursorResult<>(Collections.emptyList(), false);
		when(newsService.getNewsList(any(RequestNewsDto.class))).thenReturn(emptyResult);

		final String necessaryQueryString = "?startDateTime=2019-07-01T00:00";
		mockMvc.perform(get(NEWS_API_URL + necessaryQueryString))
				.andExpect(status().isNoContent());
	}
}