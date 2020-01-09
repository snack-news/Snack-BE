package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class PicksRepositoryTest {

	@Autowired
	private PicksRepository picksRepository;

	@Test
	@DisplayName("클라이언트에 보여질 picks 리스트를 시간 역순으로 정렬하여 가져온다")
	void getPicksListTest() {
		List<Pick> pickList = picksRepository.findAll(Sort.by(Sort.Direction.DESC, "publishAt"));

		assertAll("sort",
				() -> assertThat(pickList.size()).isEqualTo(3),
				() -> assertThat(pickList.get(0).getPublishAt()).isAfter(pickList.get(1).getPublishAt()),
				() -> assertThat(pickList.get(1).getPublishAt()).isAfter(pickList.get(2).getPublishAt())
		);
	}
}
