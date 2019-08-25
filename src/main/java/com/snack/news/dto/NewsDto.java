package com.snack.news.dto;

import com.snack.news.domain.Category;
import com.snack.news.domain.News;
import com.snack.news.domain.Tag;
import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicType;
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
public class NewsDto {

	private Long id;
	@NotNull(message = "News title")
	private String title;
	@NotNull(message = "News content")
	private String content;
	private String link;
	@NotNull(message = "Category ID")
	private Long categoryId;
	private TopicType type;
	private List<Long> topicIds;
	private List<Long> tagIds;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDateTime;

	public News toEntity(Category category, List<Topic> topics, List<Tag> tags) {
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