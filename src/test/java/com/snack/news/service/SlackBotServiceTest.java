package com.snack.news.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SlackBotServiceTest {

	@Test
	void responseMappingTest() throws JsonProcessingException {
		String response = "{\"ok\":true,\"app_id\":\"abc\",\"authed_user\":{\"def\":\"abc\"},\"scope\":\"incoming-webhook\",\"token_type\":\"bot\",\"access_token\":\"ghi\",\"bot_user_id\":\"jkl\",\"team\":{\"id\":\"mno\",\"name\":\"LRR\"},\"enterprise\":null,\"incoming_webhook\":{\"channel\":\"#snak-test\",\"channel_id\":\"stu\",\"configuration_url\":\"pqr\",\"url\":\"vwx\"}}";
		SlackBotService.SlackAuthResponse rp = new SlackBotService.SlackAuthResponse(response);
		assertDoesNotThrow(rp::toEntity);
	}
}