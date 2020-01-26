package com.snack.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Wrapper<T> {
	private T data;
}