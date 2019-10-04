package com.snack.news.config;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class MetaControllerTest {
	private final static String PROFILE_API_URL = "/meta/profile";

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private TestRestTemplate restTemplate;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	public void Profile를_확인할_수_있다() {
		String profile = this.restTemplate.getForObject(PROFILE_API_URL, String.class);

		assertThat(profile).isEqualTo("local");
	}

	@Test
	public void Profile_API는_CORS를_허용한다() throws Exception {
		mockMvc.perform(get(PROFILE_API_URL)
				.header(HttpHeaders.ORIGIN, "http://cross.domain")
				.header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET)
		)
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(corsResponseHeaders());
	}

	private ResultMatcher corsResponseHeaders() {
		return result -> header().exists(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN).match(result);
	}
}