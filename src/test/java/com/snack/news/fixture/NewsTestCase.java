package com.snack.news.fixture;

import org.junit.Before;

import com.snack.news.domain.News;

public class NewsTestCase {
	protected final String TEST_TITLE = "TEST TITLE";
	protected final String TEST_CONTENT = "TEST CONTENT";
	protected News mockNews;

	@Before
	public void setUp(){
		mockNews = News.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.build();

	}
}
