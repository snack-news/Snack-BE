package com.snack.news.fixture;

import com.snack.news.domain.news.News;
import com.snack.news.dto.AdminNewsDto;
import com.snack.news.dto.NewsDto;
import org.junit.Before;

import java.time.LocalDateTime;

public abstract class NewsFixture {
	protected static final String TEST_TITLE = "Snack-Title";
	protected static final String TEST_CONTENT = "Snack-Content";
	protected static final String TEST_LINK = "https://snack-mockNews.com";
	protected static final long TEST_SOME_ID_LONG = Long.MAX_VALUE;
	protected static final LocalDateTime VALID_START_DATE = LocalDateTime.of(2019, 8, 12, 0, 0);
	protected static final LocalDateTime VALID_END_DATE = LocalDateTime.of(2019, 8, 14, 0, 0);
	protected static final LocalDateTime INVALID_START_DATE = LocalDateTime.of(2019, 8, 11, 0, 0);
	protected static final LocalDateTime INVALID_END_DATE = LocalDateTime.of(2019, 8, 19, 0, 0);

	protected News mockNews;
	protected NewsDto mockNewsDto;
	protected AdminNewsDto mockAdminNewsDto;

	@Before
	public void setUp() {
		mockNewsDto = NewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.startDateTime(VALID_START_DATE)
				.endDateTime(VALID_END_DATE)
				.build();

		mockNews = News.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.build();

		mockAdminNewsDto = AdminNewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.startDateTime(VALID_START_DATE)
				.endDateTime(VALID_END_DATE)
				.build();

	}
}