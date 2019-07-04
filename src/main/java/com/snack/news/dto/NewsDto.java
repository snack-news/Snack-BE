package com.snack.news.dto;

import com.snack.news.domain.Category;
import com.snack.news.domain.News;
import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
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

	public News toEntity(List<Topic> topics) {
		return News.builder()
				.title(title)
				.content(content)
				.category(category)
				.topics(topics)
				.link(link)
				.build();
	}
}