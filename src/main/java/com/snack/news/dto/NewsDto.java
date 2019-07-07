package com.snack.news.dto;

import com.snack.news.domain.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {

	private Long id;
	private String title;
	private String content;
	private String link;
	private Category category;
	private TopicType type;
	private List<Long> topicIds;
	private List<Long> tagIds;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	public News toEntity() {
		return News.builder()
				.title(title)
				.content(content)
				.category(category)
				.link(link)
				.build();
	}

	public News toEntity(List<Topic> topics, List<Tag> tags) {
		return News.builder()
				.title(title)
				.content(content)
				.category(category)
				.topics(topics)
				.tags(tags)
				.link(link)
				.build();
	}
}