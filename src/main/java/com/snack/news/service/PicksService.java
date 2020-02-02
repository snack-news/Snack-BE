package com.snack.news.service;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.ListCursorResult;
import com.snack.news.dto.RequestInquiryDto;
import com.snack.news.exception.PicksNotFoundException;
import com.snack.news.repository.PicksRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class PicksService {

	private final PicksRepository picksRepository;

	public ListCursorResult<Pick> getPickList(RequestInquiryDto requestInquiryDto) {
		if (Objects.nonNull(requestInquiryDto.getLastId())) {
			requestInquiryDto.setEndDateTime(picksRepository.findById(requestInquiryDto.getLastId()).orElseThrow(PicksNotFoundException::new).getPublishAt());
		}

		List<Pick> picksList = picksRepository.findByPickDto(requestInquiryDto);
		return new ListCursorResult<>(picksList, hasNext(picksList, requestInquiryDto));
	}

	private boolean hasNext(List<Pick> list, RequestInquiryDto newsDto) {
		if(list.isEmpty() || list.size() < newsDto.getLimitSize()) {
			return false;
		}
		return picksRepository.existsByPublishAtBefore(getLastElementInList(list).getPublishAt());
	}

	private <E> E getLastElementInList(List<E> list) {
		return list.get(list.size() - 1);
	}
}