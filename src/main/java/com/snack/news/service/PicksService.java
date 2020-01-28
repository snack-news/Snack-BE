package com.snack.news.service;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.repository.PicksRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PicksService {

	private final PicksRepository picksRepository;

	public ListCursorResult<Pick> getPickScrollPage(long lastPickId, int pageSize) {
		PageRequest pageRequest = PageRequest.of(0, pageSize + 1);
		List<Pick> pickList = picksRepository.findByIdLessThanOrderByPublishAtDesc(lastPickId, pageRequest).getContent();
		return new ListCursorResult<>(pickList, hasNextPicks(pickList, pageSize));
	}

	private boolean hasNextPicks(List<Pick> pickList, int pageSize) {
		return pickList.size() > pageSize;
	}
}