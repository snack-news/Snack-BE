package com.snack.news.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@ToString
@Entity
public class News extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	@Column
	private String link;

	@ManyToMany /*(mappedBy = "news", cascade = CascadeType.ALL, fetch = FetchType.EAGER)*/
	private List<Topic> topics;

	// todo
	// News - Category

	@Builder
	public News(String title, String link, String content, List<Topic> topics) {
		this.title = title;
		this.link = link;
		this.content = content;
		this.topics = topics;
	}
}