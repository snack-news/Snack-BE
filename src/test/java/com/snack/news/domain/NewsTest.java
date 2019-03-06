package com.snack.news.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NewsTest {
	private News mockNews;

	@Before
	public void setUp() {
		mockNews = News.builder().build();
	}

	@Test
	public void 뉴스에_제목을_입력_조회_할수있다() {
		String testTitle = "Snack News";

		mockNews.setTitle(testTitle);

		assertThat(mockNews.getTitle(), is(testTitle));
	}
}

