package com.snack.news.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.snack.news.domain.Corporation;
import com.snack.news.dto.CorpDto;
import com.snack.news.exception.CorpNotFoundException;

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

	@Test
	public void updateCorp_회사를_수정_할_수_있다() {
		CorpDto corpDto = CorpDto.builder().name(TEST_CORP_NAME).image(TEST_IMAGE_URL).build();
		Corporation createdCorp = corpService.createCorp(corpDto);

		CorpDto updateCorpDto = CorpDto.builder()
				.id(createdCorp.getId())
				.name(createdCorp.getName())
				.image("UPDATE_IMAGE")
				.build();
		corpService.updateCorp(updateCorpDto);

		Corporation updatedCorp = corpService.getCorp(updateCorpDto);
		assertThat(updatedCorp.getImage()).isEqualTo("UPDATE_IMAGE");
	}

	@Test(expected = CorpNotFoundException.class)
	public void updateCorp_수정하려는_회사ID가_유효하지_않는_경우_예외를_반환한다() {
		Long invalidID = 123L;
		CorpDto updateCorpDto = CorpDto.builder()
				.id(invalidID)
				.name("UPDATE_NAME")
				.image("UPDATE_IMAGE")
				.build();

		corpService.updateCorp(updateCorpDto);
	}
}