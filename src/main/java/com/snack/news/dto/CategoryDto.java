package com.snack.news.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snack.news.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
	private Long id;
	@NotNull(message = "Category title")
	private String title;

	@JsonIgnore
	public Category getNewEntity() {
		return Category.builder()
				.title(title)
				.build();
	}

	@JsonIgnore
	public Category getUpdateEntity() {
		return Category.builder()
				.id(id)
				.title(title)
				.build();
	}
}