package com.snack.news.service;

import com.snack.news.domain.Corporation;
import com.snack.news.dto.CorpDto;
import com.snack.news.exception.CorpNotFoundException;
import com.snack.news.repository.CorpRepository;
import com.snack.news.strategy.Sorting;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class CorpService {
	private final CorpRepository corpRepository;

	public List<Corporation> getCorpList(Sorting sorting) {
		List<Corporation> corporations = corpRepository.findAll();

		return corporations.stream()
				.sorted(sorting.getOperator())
				.collect(toList());
	}

	@Transactional
	public Corporation createCorp(CorpDto corpDto) {
		Corporation corp = corpDto.getCorpNewEntity();

		corpRepository.save(corp);

		return corp; // todo : Success Response
	}

	@Transactional
	public Corporation updateCorp(CorpDto corpDto) {
		Corporation corp = corpDto.getCorpUpdateEntity();
		corpRepository.findById(corp.getId()).orElseThrow(CorpNotFoundException::new);

		return corpRepository.save(corp);
	}

	public Corporation getCorp(CorpDto corpDto) {
		return corpRepository.findById(corpDto.getId()).orElseThrow(CorpNotFoundException::new);
	}
}
