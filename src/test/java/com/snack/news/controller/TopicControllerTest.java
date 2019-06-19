package com.snack.news.controller;


import com.google.gson.Gson;
import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import com.snack.news.dto.TopicDto;
import com.snack.news.repository.TopicRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicControllerTest {

	private final static String TOPIC_API_URL = "/api/topic";

	@Autowired
	private TopicRepository topicRepository;
	@Autowired
	private WebApplicationContext context;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.context)
				.build();
	}

	@Test
	@Transactional
	public void 토픽_생성_요청이_정상적으로_이루어진다() throws Exception {

		final String testTopicName = Long.toString(ThreadLocalRandom.current().nextLong());
		final TopicType testTopicType = TopicType.CORP;

		TopicDto topicDto = TopicDto.builder().name(testTopicName).type(testTopicType).build();
		String requestJsonBody = new Gson().toJson(topicDto);


		mockMvc.perform(post(TOPIC_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().isOk());
	}

	@Test
	@Transactional
	public void 토픽_이름이_중복이라면_토픽_생성요청이_실패한다() throws Exception {
		final String testTopicName = "삼성";
		final TopicType testTopicType = TopicType.CORP;

		TopicDto topicDto = TopicDto.builder().name(testTopicName).type(testTopicType).build();
		String requestJsonBody = new Gson().toJson(topicDto);

		mockMvc.perform(post(TOPIC_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().is4xxClientError()); // todo: 자세한 오류 메시지 명시
	}

	@Test
	@Transactional
	public void 토픽_타입이_없다면_토픽_생성요청이_실패한다() throws Exception {
		final String testTopicName = Long.toString(ThreadLocalRandom.current().nextLong());
		final TopicType testTopicType = TopicType.NONE;

		TopicDto topicDto = TopicDto.builder().name(testTopicName).type(testTopicType).build();
		String requestJsonBody = new Gson().toJson(topicDto).replace(testTopicType.name(), "WRONG_TYPE");

		mockMvc.perform(post(TOPIC_API_URL)
				.contentType(MediaType.APPLICATION_JSON).content(requestJsonBody))
				.andExpect(status().is4xxClientError()); // todo: 자세한 오류 메시지 명시
	}

	@Test
	@Transactional
	public void 토픽_리스트를_정상적으로_가져온다() throws Exception {
		int realTopicListSize = topicRepository.findAll().size();

		MvcResult mvcResult = mockMvc.perform(get(TOPIC_API_URL))
				.andExpect(status().isOk())
				.andReturn();

		String responseString = mvcResult.getResponse().getContentAsString();
		Topic[] responseTopics = new Gson().fromJson(responseString, Topic[].class);

		assertThat(realTopicListSize).isEqualTo(responseTopics.length);
	}

	@Test
	@Transactional
	public void 원하는_타입의_토픽_리스트를_정상적으로_가져온다() throws Exception {
		final TopicType testTopicType = TopicType.CORP;

		List<Topic> realTopicList = topicRepository.findAllByTypeIs(testTopicType);

		MvcResult mvcResult = mockMvc.perform(get(TOPIC_API_URL + "/CORP"))
				.andExpect(status().isOk())
				.andReturn();

		String responseString = mvcResult.getResponse().getContentAsString();
		Topic[] responseTopics = new Gson().fromJson(responseString, Topic[].class);

		assertThat(responseTopics).containsAll(realTopicList);
	}

}
