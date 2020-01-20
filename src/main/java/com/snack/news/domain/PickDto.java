package com.snack.news.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snack.news.domain.base.BaseTimeEntity;
import com.snack.news.domain.picks.Pick;
import com.snack.news.domain.topic.Topic;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PickDto extends BaseTimeEntity {

	private Long id;
	@NotNull(message = "Link URL")
	private String link;
	private List<String> topicNames;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime createAt;
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
