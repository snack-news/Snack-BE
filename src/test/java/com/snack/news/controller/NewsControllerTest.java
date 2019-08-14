package com.snack.news.controller;


import com.google.gson.Gson;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.fixture.NewsTestcase;
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

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class NewsControllerTest extends NewsTestcase {

	@InjectMocks
	private NewsController newsController;

	private final static String NEWS_API_URL = "/api/news";

	@Mock
	private NewsService newsService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(newsController).build();
	}

	@Test
	public void 뉴스_생성_요청이_정상적으로_이루어진다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.categoryId(TEST_SOME_ID_LONG)
				.build();
		
		String requestJsonBody = new Gson().toJson(incorrectRequestNewsDtoForCreateNews);

		when(newsService.createNews(any(NewsDto.class))).thenReturn(mockNewsDto);

		mockMvc.perform(post(NEWS_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isOk());
	}

	@Test
	public void 뉴스_생성_요청시_제목을_입력하지_않으면_BADREQUEST_상태코드로_응답한다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.content(TEST_CONTENT)
				.categoryId(TEST_SOME_ID_LONG)
				.build();

		String requestJsonBody = new Gson().toJson(incorrectRequestNewsDtoForCreateNews);

		mockMvc.perform(post(NEWS_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void 뉴스_생성_요청시_내용을_입력하지_않으면_BADREQUEST_상태코드로_응답한다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.title(TEST_TITLE)
				.categoryId(TEST_SOME_ID_LONG)
				.build();

		String requestJsonBody = new Gson().toJson(incorrectRequestNewsDtoForCreateNews);

		mockMvc.perform(post(NEWS_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void 뉴스_생성_요청시_카테고리ID를_입력하지_않으면_BADREQUEST_상태코드로_응답한다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.build();

		String requestJsonBody = new Gson().toJson(incorrectRequestNewsDtoForCreateNews);

		mockMvc.perform(post(NEWS_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isBadRequest());
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

		mockMvc.perform(get(NEWS_API_URL + "/" + TEST_SOME_ID_LONG)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
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
