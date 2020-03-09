package com.snack.news.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MetaControllerTest {
	private final static String PROFILE_API_URL = "/meta/profile";

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private TestRestTemplate restTemplate;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@DisplayName("Profile를 확인할 수 있다")
	void profileTest() {
		String profile = this.restTemplate.getForObject(PROFILE_API_URL, String.class);

		assertThat(profile).isEqualTo("local");
	}

	@Test
	@DisplayName("Profil API는 CORS를 허용한다")
	void corsTest() throws Exception {
		mockMvc.perform(get(PROFILE_API_URL)
				.header(HttpHeaders.ORIGIN, "http://cross.domain")
				.header(HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD, HttpMethod.GET)
		)
				.andDo(print())
				.andExpect(status().isOk());
	}
}