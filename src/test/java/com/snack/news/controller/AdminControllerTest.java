package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.AdminNewsDto;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.advice.ControllerExceptionHandler;
import com.snack.news.fixture.NewsFixture;
import com.snack.news.service.AdminService;
import com.snack.news.util.SnackObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AdminControllerTest extends NewsFixture {
	private final static String ADMIN_API_URL = "/admin/api/news";

	@InjectMocks
	private AdminController adminController;

	@Mock
	private AdminService adminService;

	private static MockMvc mockMvc;

	@BeforeEach
	void setup() {
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
	@DisplayName("뉴스 생성 요청이 정상적으로 이루어진다")
	void requestCreateNewsTest() throws Exception {
		NewsDto correctRequestNewsDtoForCreate = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.categoryId(TEST_SOME_ID_LONG)
				.build();

		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(correctRequestNewsDtoForCreate);

		when(adminService.createNews(any(AdminNewsDto.class))).thenReturn(mockNewsDto);

		mockMvc.perform(post(ADMIN_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 생성 요청시 제목을 입력하지 않으면 BADREQUEST 상태코드로 응답한다")
	void requestCreateNewsTestWithoutTitle() throws Exception {
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
	@DisplayName("뉴스 생성 요청시 내용을 입력하지 않으면 BADREQUEST 상태코드로 응답한다")
	void requestCreateNewsWithoutContent() throws Exception {
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
	@DisplayName("뉴스 생성 요청시 카테고리ID를 입력하지 않으면 BADREQUEST 상태코드로 응답한다")
	void requestCreateNewsTestWithoutCategoryId() throws Exception {
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
	@DisplayName("어드민 페이지에서 뉴스리스트를 정상적으로 가져온다")
	void requestAdminNewsListTest() throws Exception {
		Page<News> dummyNewsPage = new PageImpl<>(Collections.singletonList(News.builder().build()));
		when(adminService.getNewsList(1)).thenReturn(dummyNewsPage);

		mockMvc.perform(get(ADMIN_API_URL + "/1"))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("어드민 페이지에서 페이지를 지정하지 않는다면 1페이지를 가져온다")
	void requestAdminNewsListTestWithoutPageNum() throws Exception {
		Page<News> dummyNewsPage = new PageImpl<>(Collections.singletonList(News.builder().build()));
		when(adminService.getNewsList(1)).thenReturn(dummyNewsPage);

		mockMvc.perform(get(ADMIN_API_URL))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 수정 요청이 정상적으로 이루어진다")
	void requestNewsListTest() throws Exception {
		NewsDto correctRequestNewsDtoForUpdate = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.categoryId(TEST_SOME_ID_LONG)
				.build();

		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(correctRequestNewsDtoForUpdate);

		final long anyLong = 1;
		mockMvc.perform(put(ADMIN_API_URL + "/" + anyLong)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 삭제 요청시 정상적으로 동작한다")
	void requestDeleteNewsTest() throws Exception {
		mockMvc.perform(delete(ADMIN_API_URL + "/" + anyLong()))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("뉴스 삭제 요청시 ID가 없으면 NOTFOUND 상태코드로 응답한다")
	void requestDeleteNewsTestWithoutNewsId() throws Exception {
		doThrow(new NewsNotFoundException()).when(adminService).deleteNews(anyLong());
		mockMvc.perform(delete(ADMIN_API_URL + "/" + anyLong()))
				.andExpect(status().isNotFound());
	}
}
