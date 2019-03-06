package com.snack.news.dto;

import com.snack.news.domain.News;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsDto {
	private String title;
	private String content;
	private String link;

	public News toEntity() {
		return News.builder()
				.title(title)
				.content(content)
				.link(link)
				.build();
	}
}