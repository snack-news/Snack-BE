package com.snack.news.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@Getter
@Setter
public class AuthDto {
	@NotNull
	@Size(min = 1, max = 50)
	private String username;

	@NotNull
	@Size(min = 4, max = 100)
	private String password;
}
