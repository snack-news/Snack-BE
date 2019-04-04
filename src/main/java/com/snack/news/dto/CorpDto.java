package com.snack.news.dto;

import lombok.Builder;

import com.snack.news.domain.Corporation;

public class CorpDto {
	private Long id;
	private String name;
	private String image;

	@Builder
	public CorpDto(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public CorpDto(Corporation corp) {
		this.id = corp.getId();
		this.name = corp.getName();
	}

	public Corporation getCorpEntity() {
		return Corporation.builder()
				.name(name)
				.image(image)
				.build();
	}
}
