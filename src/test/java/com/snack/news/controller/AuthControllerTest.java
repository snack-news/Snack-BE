package com.snack.news.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		SecurityContextHolder.clearContext();
	}

	@Test
	public void successfulAuthenticationWithUser() throws Exception {
		getMockMvc().perform(post("/api/auth")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"password\": \"password\", \"username\": \"user\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("token")));
	}

	public MockMvc getMockMvc() {
		return mockMvc;
	}
}
