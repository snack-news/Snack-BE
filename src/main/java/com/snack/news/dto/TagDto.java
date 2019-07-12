package com.snack.news.dto;


import com.snack.news.domain.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TagDto {
	private Long id;
	private String title;

	public Tag getNewEntity() {
		return Tag.builder().title(title).build();
	}

	public Tag getUpdateEntity() {
		return Tag.builder().id(id).title(title).build();
	}
}
