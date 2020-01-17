package com.snack.news.service;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.PicksDto;
import com.snack.news.repository.PicksRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PicksService {

	private final static Sort SORT_BY_ID = Sort.by(Sort.Direction.DESC, "publish_at");
	private final static int DEFAULT_PAGING_SIZE = 5;

	private final PicksRepository picksRepository;

	public PicksDto getPickScrollPage(long lastPickId) {
		return getPickScrollPage(lastPickId, DEFAULT_PAGING_SIZE);
	}

	public PicksDto getPickScrollPage(long lastPickId, int pageSize) {
		PageRequest pageRequest = PageRequest.of(0, pageSize + 1);
		List<Pick> pickList = picksRepository.findByIdLessThanOrderByIdDesc(lastPickId, pageRequest).getContent();

		return new PicksDto(pickList, hasNextPicks(pickList));
	}

	private boolean hasNextPicks(List<Pick> pickList) {
		return pickList.size() < DEFAULT_PAGING_SIZE;
	}
}

