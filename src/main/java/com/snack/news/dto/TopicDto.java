package com.snack.news.dto;

import com.snack.news.domain.Topic;
import com.snack.news.domain.TopicType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TopicDto {
	private Long id;
	private String name;
	private String image;
	private String type = TopicType.NONE.name();


	@Builder
	public TopicDto(Long id, String type, String name, String image) {
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
