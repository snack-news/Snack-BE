package com.snack.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snack.news.domain.picks.Pick;
import com.snack.news.domain.topic.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PickDto {
	private Long id;
	@NotNull(message = "Link")
	private String link;
	private List<String> topicNames;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime publishAt;

	@JsonIgnore
	public Pick toEntity(List<Topic> topics) {
		return Pick.builder()
				.topics(topics)
				.link(link)
				.publishAt(publishAt)
				.build();
	}
}
