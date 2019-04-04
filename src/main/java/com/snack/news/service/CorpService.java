package com.snack.news.service;

import lombok.AllArgsConstructor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.snack.news.domain.Corporation;
import com.snack.news.dto.CorpDto;
import com.snack.news.repository.CorpRepository;

@AllArgsConstructor
@Service
public class CorpService {
	private final CorpRepository corpRepository;

	public List<Corporation> getCorpList() {
		return corpRepository.findAll();
	}

	@Transactional
	public Corporation createCorp(CorpDto corpDto) {
		Corporation corp = corpDto.getCorpEntity();

		corpRepository.save(corp);

		return corp;
	}
}
