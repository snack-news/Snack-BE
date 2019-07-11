package com.snack.news.dto;

import com.snack.news.domain.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDateTime;

	public News toEntity() {
		return News.builder()
				.title(title)
				.content(content)
				.category(category)
				.link(link)
				.build();
	}

	public News toEntity(Category category, List<Topic> topics, List<Tag> tags) {
		return News.builder()
				.title(title)
				.content(content)
				.category(category)
				.topics(topics)
				.link(link)
				.build();
	}
}