package com.snack.news.service;

import com.snack.news.domain.picks.Pick;
import com.snack.news.repository.PicksRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PicksService {

	private final static Sort SORT_BY_ID = Sort.by(Sort.Direction.DESC, "publish_at");
	private final static int DEFAULT_PAGING_SIZE = 10;

	private final PicksRepository picksRepository;

	public Page<Pick> getPickPage(long page) {
		Pageable pageable = PageRequest.of((int) page - 1, DEFAULT_PAGING_SIZE, SORT_BY_ID);
		return picksRepository.findAll(pageable);
	}

	public Page<Pick> getPickPage(long lastPickId, int size) {
		PageRequest pageRequest = PageRequest.of(0, size);
		return picksRepository.findByIdLessThanOrderByIdDesc(lastPickId, pageRequest);
	}
}

