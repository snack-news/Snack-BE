package com.snack.news.domain.picks;

import com.snack.news.domain.base.BaseTimeEntity;
import com.snack.news.domain.category.Category;
import com.snack.news.domain.topic.Topic;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class Pick extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String link;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "pick_topic",
			joinColumns = @JoinColumn(name = "pick_id"),
			inverseJoinColumns = @JoinColumn(name = "topic_id"))
	private List<Topic> topics;

	@Column
	private LocalDateTime publishAt;

	@Builder
	public Pick(String link, Category category, List<Topic> topics, LocalDateTime publishAt, LocalDateTime createAt) {
		this.link = link;
		this.category = category;
		this.topics = topics;
		this.publishAt = Optional.ofNullable(publishAt).orElse(LocalDateTime.now());
		setCreateAt(createAt);
	}
}