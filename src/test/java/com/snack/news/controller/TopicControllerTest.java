package com.snack.news.controller;

import com.snack.news.domain.topic.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.fixture.TopicFixture;
import com.snack.news.service.TopicService;
import com.snack.news.util.SnackObjectMapper;
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

import java.util.concurrent.ThreadLocalRandom;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class TopicControllerTest extends TopicFixture {
	private final static String TOPIC_API_URL = "/api/topic";

	@InjectMocks
	private TopicController topicController;
	@Mock
	private TopicService topicService;

	private MockMvc mockMvc;


	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(topicController).build();
	}

	@Test
	@DisplayName("토픽 생성 요청이 정상적으로 이루어진다")
	void requestCreateTopicTest() throws Exception {
		TopicDto topicDtoWithNameAndType = TopicFixture.TEST_TOPIC_DTO_FOR_CORRECT_REQUEST;
		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(topicDtoWithNameAndType);

		when(topicService.createTopic(any(TopicDto.class))).thenReturn(TopicFixture.DUMMY);

		mockMvc.perform(post(TOPIC_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("토픽 타입이 없다면 토픽 생성요청이 실패한다")
	void requestTopicTestWithoutTopicType() throws Exception {
		final String randomTopicName = Long.toString(ThreadLocalRandom.current().nextLong());
		final TopicType testTopicType = TopicType.NONE;

		TopicDto requestTopicDto = TopicDto.builder().name(randomTopicName).type(testTopicType).build();
		String requestJsonBody = SnackObjectMapper.mapper.writeValueAsString(requestTopicDto).replace(testTopicType.name(), "SOME_WRONG_TYPE");

		mockMvc.perform(post(TOPIC_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().is4xxClientError()); // todo: 자세한 오류 메시지 명시
	}

	@Test
	@DisplayName("원하는 타입의 토픽 리스트를 가져온다")
	void requestTopicListAsTopicType() throws Exception {
		mockMvc.perform(get(TOPIC_API_URL + "/CORP"))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("원하는 타입의 토픽 리스트를 ID순으로 가져온다")
	void requestTopicListAsTopicTypeInOrderOfId() throws Exception {
		mockMvc.perform(get(TOPIC_API_URL + "/CORP"+ "?sort=ID"))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("원하는 타입의 토픽 리스트를 이름순으로 가져온다")
	void requestTopicListAsTopicTypeInOrderOfName() throws Exception {
		mockMvc.perform(get(TOPIC_API_URL + "/CORP" + "?sort=NAME"))
				.andExpect(status().isOk());
	}
}