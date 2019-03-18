package com.snack.news.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class News extends BaseTimeEntity {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String title;
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	@Column
	private String link;

	@Builder
	public News(String title, String link, String content) {
		this.title = title;
		this.link = link;
		this.content = content;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
