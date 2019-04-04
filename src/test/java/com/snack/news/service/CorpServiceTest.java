package com.snack.news.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.snack.news.domain.Corporation;
import com.snack.news.dto.CorpDto;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CorpServiceTest {
	private static final String TEST_CORP_NAME = "TEST_NAME";
	private static final String TEST_IMAGE_URL = "IMAGE_URL";

	@Autowired
	private CorpService corpService;

	@Test
	public void createCorp_회사를_등록_및_조회_할_수_있다() {
		CorpDto corpDto = CorpDto.builder().name(TEST_CORP_NAME).image(TEST_IMAGE_URL).build();
		Corporation corp = corpService.createCorp(corpDto);

		List<Corporation> corpList = corpService.getCorpList();

		assertThat(corpList).contains(corp);
	}
}