package com.snack.news.controller;


import com.snack.news.domain.news.News;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.RequestQueryDto;
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

import java.util.Collections;

import static com.snack.news.controller.ApiUrl.Domain.NEWS;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class NewsControllerTest extends NewsFixture {

	@InjectMocks
	private NewsController newsController;

	@Mock
	private NewsService newsService;

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(newsController)
				.setControllerAdvice(new ControllerExceptionHandler())
				.build();
	}

	@Test
	@DisplayName("뉴스 조회 요청이 정상적으로 이루어진다")
	void requestCreateNewsTest() throws Exception {
		mockMvc.perform(get(ApiUrl.builder().get(NEWS).id(TEST_SOME_ID_LONG).build())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 조회 요청이 ID가 부적절하다면 NOTFOUND 상태코드로 응답한다")
	void requestCreateNewsTestWithInvalidNewsId() throws Exception {
		when(newsService.getNews(anyLong())).thenThrow(NewsNotFoundException.class);

		mockMvc.perform(get(ApiUrl.builder().get(NEWS).id((anyLong())).build())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("뉴스 리스트 조회 요청시 값이 있다면 OK 상태코드로 응답한다")
	void requestNewsListTest() throws Exception {

		when(newsService.getNewsList(any(RequestQueryDto.class))).thenReturn(mockNewsResult);

		final String necessaryQueryString = "?startDateTime=2019-07-01T00:00";
		mockMvc.perform(get(ApiUrl.builder().get(NEWS).list().query(necessaryQueryString).build()))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 리스트 조회 요청시 값이 없다면 NOCONTENT 상태코드로 응답한다")
	void requestNewsListTestWhenNoneResult() throws Exception {
		ListCursorResult<News> emptyResult = new ListCursorResult<>(Collections.emptyList(), false);
		when(newsService.getNewsList(any(RequestQueryDto.class))).thenReturn(emptyResult);

		final String necessaryQueryString = "?startDateTime=2019-07-01T00:00";
		mockMvc.perform(get(ApiUrl.builder().get(NEWS).list().query(necessaryQueryString).build()))
				.andExpect(status().isNoContent());
	}
}