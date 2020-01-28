package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class PicksRepositoryTest {

	@Autowired
	private PicksRepository picksRepository;

	@Test
	@DisplayName("Pick을 저장할 수 있다")
	void createPick() {
		Pick pick = Pick.builder().link("link").build();
		int originalSize = (int) picksRepository.count();
		picksRepository.save(pick);

		List<Pick> pickList = picksRepository.findAll();
		assertThat(pickList.size()).isEqualTo(originalSize + 1);
	}

	@Test
	@DisplayName("클라이언트에 보여질 picks 리스트를 시간 역순으로 정렬하여 가져온다")
	void getPicksListTest() {
		List<Pick> pickList = picksRepository.findAll(Sort.by(Sort.Direction.DESC, "publishAt"));
		assertThat(pickList.stream().map(Pick::getPublishAt)).isSortedAccordingTo(Comparator.reverseOrder());
	}

	@Test
	@DisplayName("마지막으로 본 것 이후의 pick을 차례대로 가져온다")
	void getPicksListTestForInfinityScroll() {

		final int pageSize = 4;
		final int lastPickId = 8;
		PageRequest pageRequest = PageRequest.of(0, pageSize);
		Page<Pick> actualPicksResult = picksRepository.findByIdLessThanOrderByPublishAtDesc(lastPickId, pageRequest);

		List<Pick> expectedPicksResult = picksRepository.findAll().stream()
				.filter(p -> p.getId() < lastPickId)
				.sorted(Comparator.comparing(Pick::getPublishAt).reversed())
				.limit(pageSize)
				.collect(Collectors.toList());

		assertThat(actualPicksResult).containsExactlyInAnyOrderElementsOf(expectedPicksResult);
	}
}
