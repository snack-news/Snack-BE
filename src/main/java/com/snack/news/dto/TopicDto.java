package com.snack.news.dto;

import com.snack.news.domain.topic.Topic;
import com.snack.news.domain.topic.TopicType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class TopicDto {
	private Long id;
	@NotNull(message = "Topic title")
	private String name;
	private String image;
	private TopicType type = TopicType.NONE;

	@Builder
	public TopicDto(Long id, TopicType type, String name, String image) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.image = image;
	}

	public TopicDto(Topic topic) {
		this.id = topic.getId();
		this.name = topic.getName();
	}

	public Topic getTopicNewEntity() {
		return Topic.builder()
				.type(type)
				.name(name)
				.image(image)
				.build();
	}

	public Topic getTopicUpdateEntity() {
		return Topic.builder()
				.id(id)
				.name(name)
				.image(image)
				.type(type)
				.build();
	}
}
