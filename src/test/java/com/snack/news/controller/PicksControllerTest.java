package com.snack.news.controller;

import com.snack.news.exception.advice.ControllerExceptionHandler;
import com.snack.news.service.PicksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import java.lang.reflect.Method;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class PicksControllerTest {
	private final static String PICK_API_URL = "/api";

	@InjectMocks
	private PicksController picksController;

	@Mock
	private PicksService picksService;

	private static MockMvc mockMvc;

	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(picksController)
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
	@DisplayName("Pick 조회 요청이 정상적으로 이루어진다")
	void requestGetPickListTest() throws Exception {
		mockMvc.perform(get(PICK_API_URL + "/picks"))
				.andExpect(status().isOk());
	}
}
