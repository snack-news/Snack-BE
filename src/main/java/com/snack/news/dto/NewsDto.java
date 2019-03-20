package com.snack.news.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.snack.news.domain.News;

@Getter
@Setter
@Builder
public class NewsDto {
	private Long id;
	private String title;
	private String content;
	private String link;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	public News toEntity() {
		return News.builder()
				.title(title)
				.content(content)
				.link(link)
				.build();
	}
}