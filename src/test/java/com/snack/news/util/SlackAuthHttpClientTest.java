package com.snack.news.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class SlackAuthHttpClientTest {

	@Test
	void responseMappingTest() throws JsonProcessingException {
		String response = "{\"ok\":true,\"app_id\":\"abc\",\"authed_user\":{\"id\":\"abc\"},\"scope\":\"incoming-webhook\",\"token_type\":\"bot\",\"access_token\":\"ghi\",\"bot_user_id\":\"jkl\",\"team\":{\"id\":\"mno\",\"name\":\"LRR\"},\"enterprise\":null,\"incoming_webhook\":{\"channel\":\"#snak-test\",\"channel_id\":\"stu\",\"configuration_url\":\"pqr\",\"url\":\"vwx\"}}";
		SlackAuthHttpClient.Response rp = new SlackAuthHttpClient.Response(response);
		assertDoesNotThrow(rp::toEntity);
	}
}
