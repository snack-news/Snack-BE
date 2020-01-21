package com.snack.news.fixture;

import com.snack.news.domain.news.News;
import com.snack.news.dto.AdminNewsDto;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.RequestNewsDto;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public abstract class NewsFixture {
	protected static final String TEST_TITLE = "Snack-Title";
	protected static final String TEST_CONTENT = "Snack-Content";
	protected static final String TEST_LINK = "https://snack-mockNews.com";
	protected static final long TEST_SOME_ID_LONG = Long.MAX_VALUE;
	protected static final LocalDateTime VALID_START_DATE = LocalDateTime.of(2019, 8, 12, 0, 0);
	protected static final LocalDateTime VALID_END_DATE = LocalDateTime.of(2019, 8, 14, 0, 0);
	protected static final LocalDateTime INVALID_START_DATE = LocalDateTime.of(2019, 8, 11, 0, 0);
	protected static final LocalDateTime INVALID_END_DATE = LocalDateTime.of(2019, 8, 19, 0, 0);
	protected static final int TEST_LIMIT_SIZE = 9;

	protected News mockNews;
	protected NewsDto mockNewsDto;
	protected RequestNewsDto mockRequestNewsDto;
	protected AdminNewsDto mockAdminNewsDto;
	protected List<News> mockNewsList;
	protected ListCursorResult<News> mockNewsResult;

	@BeforeEach
	public void setUp() {
		mockNewsDto = NewsDto.builder()
				.id(TEST_SOME_ID_LONG)
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.build();

		mockNews = mockNewsDto.toEntity(null, null, null);

		mockRequestNewsDto = RequestNewsDto.builder()
				.startDateTime(VALID_START_DATE)
				.lastNewsId(TEST_SOME_ID_LONG)
				.limitSize(100)
				.build();

		mockAdminNewsDto = AdminNewsDto.builder()
				.title(TEST_TITLE)
				.content(TEST_CONTENT)
				.link(TEST_LINK)
				.build();

		mockNewsList = Collections.singletonList(mockNews);

		mockNewsResult = new ListCursorResult<>(mockNewsList, false);
	}
}