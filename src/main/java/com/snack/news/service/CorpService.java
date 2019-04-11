package com.snack.news.service;

import lombok.AllArgsConstructor;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.snack.news.domain.Corporation;
import com.snack.news.dto.CorpDto;
import com.snack.news.exception.CorpNotFoundException;
import com.snack.news.repository.CorpRepository;

@AllArgsConstructor
@Service
public class CorpService {
	private final CorpRepository corpRepository;

	// todo : 항상 전체를 내려주는 것이 맞을까? 수십만개 회사가 등록되다면?
	public List<Corporation> getCorpList() {
		return corpRepository.findAll();
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
