package com.snack.news.dto;

import com.snack.news.domain.Corporation;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CorpDto {
	private Long id;
	private String name;
	private String image;

	@Builder
	public CorpDto(Long id, String name, String image) {
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public CorpDto(Corporation corp) {
		this.id = corp.getId();
		this.name = corp.getName();
	}

	public Corporation getCorpNewEntity() {
		return Corporation.builder()
				.name(name)
				.image(image)
				.build();
	}

	public Corporation getCorpUpdateEntity() {
		return Corporation.builder()
				.id(id)
				.name(name)
				.image(image)
				.build();
	}
}
