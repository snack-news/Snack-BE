package com.snack.news.domain.topic;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Optional;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Topic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TopicType type;

	@Column(nullable = false, unique = true)
	private String name;

	@Column
	private String image;

	@Builder
	public Topic(Long id, String name, String image, TopicType type) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.type = Optional.ofNullable(type).orElse(TopicType.NONE);
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
