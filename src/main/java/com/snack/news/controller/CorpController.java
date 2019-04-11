package com.snack.news.controller;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snack.news.domain.Corporation;
import com.snack.news.dto.CorpDto;
import com.snack.news.service.CorpService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/corp")
public class CorpController {
	private final CorpService corpService;

	// todo : Domain이 직접 노출되는 문제
	@PostMapping
	public Corporation createCorp(@RequestBody CorpDto corpDto) {
		return corpService.createCorp(corpDto);
	}

	@GetMapping
	public List<Corporation> getCorpList() {
		return corpService.getCorpList();
	}

	@PutMapping
	public Corporation updateCorp(@RequestBody CorpDto corpDto) {
		return corpService.updateCorp(corpDto);
	}
}