package com.snack.news.domain.slack;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class SlackChannel {
	@Id
	private String channelId;
	private String channelName;
	private String configurationUrl;
	private String webhookUrl;

	private String teamId;
	private String teamName;

	private String authedUserId;
	private String accessToken;
	private String botUserId;

	@Builder
	public SlackChannel(String channelId, String channelName, String configurationUrl, String webhookUrl, String teamId, String teamName, String authedUserId, String accessToken, String botUserId) {
		this.channelId = channelId;
		this.channelName = channelName;
		this.configurationUrl = configurationUrl;
		this.webhookUrl = webhookUrl;
		this.teamId = teamId;
		this.teamName = teamName;
		this.authedUserId = authedUserId;
		this.accessToken = accessToken;
		this.botUserId = botUserId;
	}
}
