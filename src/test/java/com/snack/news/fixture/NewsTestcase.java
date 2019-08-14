package com.snack.news.fixture;

import com.snack.news.domain.News;
import com.snack.news.dto.NewsDto;
import org.junit.Before;

public abstract class NewsTestcase {
	protected static final String TEST_TITLE = "Snack-Title";
	protected static final String TEST_CONTENT = "Snack-Content";
	protected static final String TEST_LINK = "https://snack-mockNews.com";
	protected static final long TEST_SOME_ID_LONG = Long.MAX_VALUE;

	protected News mockNews;
	protected NewsDto mockNewsDto;

	@Before
	public void setUp() {
		mockNewsDto = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.build();

		mockNews = News.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.build();
	}
}