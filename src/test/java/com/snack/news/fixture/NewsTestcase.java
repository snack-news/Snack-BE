package com.snack.news.fixture;

import org.junit.Before;

import com.snack.news.domain.News;

public abstract class NewsTestcase {
	protected static final String TEST_TITLE = "Snack-Title";
	protected static final String TEST_CONTENT = "Snack-Content";
	protected static final String TEST_LINK = "https://snack-mockNews.com";

	protected News mockNews;

	@Before
	public void setUp() {
		mockNews = News.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.build();
	}
}
