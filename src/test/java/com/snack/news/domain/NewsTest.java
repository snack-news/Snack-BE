package com.snack.news.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
- 뉴스ID
    - Unique 해야함.
    - 날짜기반-YYYYMMDD  + Something?
- **제목**
    - 글자수 제한 아직 미정
    - 이모지 지원
- **태그 정보.**
    - 최소 한개. 여러개 들어갈 수 있다.
    - 회사
    - 토픽
    - 커스텀 : Top 3, Hot, Emergency
- **뉴스 시간 정보**
    - 발행일.
- **뉴스 본문.**
    - 글 1개
    - 동영상(Youtube).
    - 이미지도 포함될 수 있다.
- **기사 링크**
    - Open Graph 확인하고 없을 경우 기본 이미지 넣기.
    - 여러 개의 링크 가능성.
 */

/**
 * Tests for {@link News}.
 *
 * @author Nesoy
 */
public class NewsTest {
	private News mockNews;

	@Before
	public void setUp(){
		mockNews = new News();
	}

	@Test
	public void 뉴스에_제목을_입력_조회_할수있다(){
		String testTitle = "Snack News";

		mockNews.setTitle(testTitle);

		String result = mockNews.getTitle();
		assertEquals(result, testTitle);
//		assertThat(mockNews.getTitle()).isEqualTo(testTitle);
	}
}

