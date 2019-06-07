package com.snack.news.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
@ToString
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TopicType type = TopicType.NONE;

	@Column(nullable = false)
	private String name;

	@Column
	private String image;

	@Builder
	public Topic(Long id, String name, String image, String type) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.type = TopicType.valueOf(type);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Topic that = (Topic) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(type, that.type) &&
				Objects.equals(name, that.name) &&
				Objects.equals(image, that.image);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, type, name, image);
	}
}
