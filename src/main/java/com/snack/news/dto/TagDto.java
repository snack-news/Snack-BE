package com.snack.news.dto;


import com.snack.news.domain.tag.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
public class TagDto {
	private Long id;
	@NotNull(message = "Tag title")
	private String title;

	public Tag getNewEntity() {
		return Tag.builder().title(title).build();
	}

	public Tag getUpdateEntity() {
		return Tag.builder().id(id).title(title).build();
	}
}
