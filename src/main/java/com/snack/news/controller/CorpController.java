package com.snack.news.controller;

import com.snack.news.domain.Corporation;
import com.snack.news.dto.CorpDto;
import com.snack.news.service.CorpService;
import com.snack.news.strategy.Sorting;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public List<Corporation> getCorpList(@RequestParam(defaultValue = "NAME") Sorting sorting) {
		return corpService.getCorpList(sorting);
	}

	@PutMapping
	public Corporation updateCorp(@RequestBody CorpDto corpDto) {
		return corpService.updateCorp(corpDto);
	}
}
