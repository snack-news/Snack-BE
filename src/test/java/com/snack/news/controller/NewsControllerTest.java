package com.snack.news.controller;


import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.advice.ControllerExceptionHandler;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.service.NewsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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

@RunWith(MockitoJUnitRunner.class)
public class NewsControllerTest extends NewsFixture {

	@InjectMocks
	private NewsController newsController;

	private final static String NEWS_API_URL = "/api/news";

	@Mock
	private NewsService newsService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
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
	public void 뉴스_조회_요청이_정상적으로_이루어진다() throws Exception {
		mockMvc.perform(get(NEWS_API_URL + "/" + TEST_SOME_ID_LONG)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void 뉴스_조회_요청이_ID가_부적절하다면_NOTFOUND_상태코드로_응답한다() throws Exception {
		when(newsService.getNews(anyLong())).thenThrow(NewsNotFoundException.class);

		mockMvc.perform(get(NEWS_API_URL + "/" + anyLong())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void 뉴스_리스트_조회_요청시_값이_있다면_OK_상태코드로_응답한다() throws Exception {
		when(newsService.getNewsList(any(NewsDto.class))).thenReturn(Collections.singletonList(mockNews));

		mockMvc.perform(get(NEWS_API_URL))
				.andExpect(status().isOk());
	}

	@Test
	public void 뉴스_리스트_조회_요청시_값이_없디면_NOCONTENT_상태코드로_응답한다() throws Exception {
		when(newsService.getNewsList(any(NewsDto.class))).thenReturn(Collections.EMPTY_LIST);

		mockMvc.perform(get(NEWS_API_URL))
				.andExpect(status().isNoContent());
	}
}
