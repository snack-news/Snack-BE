package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.advice.ControllerExceptionHandler;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.service.AdminService;
import com.snack.news.util.SnackObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest extends NewsFixture {
	private final static String ADMIN_API_URL = "/admin/api/news";

	@InjectMocks
	private AdminController adminController;

	@Mock
	private AdminService adminService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(adminController)
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
	public void 뉴스_생성_요청이_정상적으로_이루어진다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.categoryId(TEST_SOME_ID_LONG)
				.build();

		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(incorrectRequestNewsDtoForCreateNews);

		when(adminService.createNews(any(NewsDto.class))).thenReturn(mockNewsDto);

		mockMvc.perform(post(ADMIN_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isOk());
	}

	@Test
	public void 뉴스_생성_요청시_제목을_입력하지_않으면_BADREQUEST_상태코드로_응답한다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.content(TEST_CONTENT)
				.categoryId(TEST_SOME_ID_LONG)
				.build();

		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(incorrectRequestNewsDtoForCreateNews);

		mockMvc.perform(post(ADMIN_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void 뉴스_생성_요청시_내용을_입력하지_않으면_BADREQUEST_상태코드로_응답한다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.title(TEST_TITLE)
				.categoryId(TEST_SOME_ID_LONG)
				.build();

		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(incorrectRequestNewsDtoForCreateNews);

		mockMvc.perform(post(ADMIN_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void 뉴스_생성_요청시_카테고리ID를_입력하지_않으면_BADREQUEST_상태코드로_응답한다() throws Exception {
		NewsDto incorrectRequestNewsDtoForCreateNews = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.build();

		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(incorrectRequestNewsDtoForCreateNews);

		mockMvc.perform(post(ADMIN_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void 어드민_페이지에서_뉴스리스트를_정상적으로_가져온다() throws Exception {
		Page<News> dummyNewsPage = new PageImpl<>(Collections.singletonList(News.builder().build()));
		when(adminService.getNewsList(1)).thenReturn(dummyNewsPage);

		mockMvc.perform(get(ADMIN_API_URL + "/1"))
				.andExpect(status().isOk());
	}

	@Test
	public void 어드민_페이지에서_페이지를_지정하지_않는다면_1페이지를_가져온다() throws Exception {
		Page<News> dummyNewsPage = new PageImpl<>(Collections.singletonList(News.builder().build()));
		when(adminService.getNewsList(1)).thenReturn(dummyNewsPage);

		mockMvc.perform(get(ADMIN_API_URL))
				.andExpect(status().isOk());
	}

	@Test
	public void 뉴스_삭제_요청시_정상적으로_동작한다() throws Exception {
		mockMvc.perform(delete(ADMIN_API_URL + "/" + anyLong()))
				.andExpect(status().isOk());
	}

	@Test
	public void 뉴스_삭제_요청시_ID가_없으면_NOTFOUND_상태코드로_응답한다() throws Exception {
		doThrow(new NewsNotFoundException()).when(adminService).deleteNews(anyLong());
		mockMvc.perform(delete(ADMIN_API_URL + "/" + anyLong()))
				.andExpect(status().isNotFound());
	}
}
