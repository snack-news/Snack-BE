package com.snack.news.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

	@JsonIgnore
	public Tag getNewEntity() {
		return Tag.builder().title(title).build();
	}

	@JsonIgnore
	public Tag getUpdateEntity() {
		return Tag.builder().id(id).title(title).build();
	}
}
