package com.snack.news.domain.news;


import com.snack.news.domain.base.BaseTimeEntity;
import com.snack.news.domain.category.Category;
import com.snack.news.domain.topic.Topic;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Getter
public abstract class Post extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column
	protected String link;

	@ManyToOne
	@JoinColumn(name = "category_id")
	protected Category category;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "news_topic",
			joinColumns = @JoinColumn(name = "news_id"),
			inverseJoinColumns = @JoinColumn(name = "topic_id"))
	protected List<Topic> topics;

	@Column
	protected LocalDateTime publishAt;
}
