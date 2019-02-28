package com.snack.news.domain;

import java.time.LocalDateTime;

public class News {
	private String title;
	private String link;
	private LocalDateTime createDate;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public String getLink(){
		return this.link;
	}
}
