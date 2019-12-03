package com.snack.news.util;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class SlackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {
	private static final String SLACK_WEB_HOOK_URL = "https://hooks.slack.com/services/";
	private static Layout<ILoggingEvent> defaultLayout = new LayoutBase<ILoggingEvent>() {
		public String doLayout(ILoggingEvent event) {
			return "-- [" + event.getLevel() + "]" +
					event.getLoggerName() + " - " +
					event.getFormattedMessage().replaceAll("\n", "\n\t");
		}
	};

	private String webhookUri;
	private String channel;
	private String username;
	private String iconEmoji;
	private String iconUrl;
	private Boolean colorCoding = false;
	private Layout<ILoggingEvent> layout = defaultLayout;
	private static final int TIME_OUT = 30_000;
	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void append(final ILoggingEvent evt) {
		try {
			if(Strings.isNotEmpty(webhookUri)) {
				sendMessageWithWebhookUri(evt);
			}
		} catch (Exception ex) {
			addError("Error posting log to Slack.com (" + channel + "): " + evt, ex);
		}
	}

	private void sendMessageWithWebhookUri(final ILoggingEvent evt) throws IOException {
		String[] parts = layout.doLayout(evt).split("\n", 2);

		Map<String, Object> message = new HashMap<>();
		message.put("channel", channel);
		message.put("username", username);
		message.put("icon_emoji", iconEmoji);
		message.put("icon_url", iconUrl);
		message.put("text", parts[0]);

		// Send the lines below the first line as an attachment.
		if (parts.length > 1 && parts[1].length() > 0) {
			Map<String, String> attachment = new HashMap<>();
			attachment.put("text", parts[1]);
			if (colorCoding) {
				attachment.put("color", colorByEvent(evt));
			}

			message.put("attachments", Collections.singletonList(attachment));
		}

		final byte[] bytes = objectMapper.writeValueAsBytes(message);
		postMessage(SLACK_WEB_HOOK_URL+webhookUri, "application/json", bytes);
	}

	private String colorByEvent(ILoggingEvent evt) {
		if (Level.ERROR.equals(evt.getLevel())) {
			return "danger";
		} else if (Level.WARN.equals(evt.getLevel())) {
			return "warning";
		} else if (Level.INFO.equals(evt.getLevel())) {
			return "good";
		}

		return "";
	}

	private void postMessage(String uri, String contentType, byte[] bytes) throws IOException {
		final HttpURLConnection conn = (HttpURLConnection) new URL(uri).openConnection();
		conn.setConnectTimeout(TIME_OUT);
		conn.setReadTimeout(TIME_OUT);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setFixedLengthStreamingMode(bytes.length);
		conn.setRequestProperty("Content-Type", contentType);

		final OutputStream os = conn.getOutputStream();
		os.write(bytes);

		os.flush();
		os.close();
	}

	public void setIconEmoji(String iconEmojiArg) {
		this.iconEmoji = iconEmojiArg;
		if (iconEmoji != null && !iconEmoji.isEmpty() && iconEmoji.startsWith(":") && !iconEmoji.endsWith(":")) {
			iconEmoji += ":";
		}
	}
}