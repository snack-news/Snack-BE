package com.snack.news.controller;

import com.snack.news.dto.PicksCursorResult;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.PicksService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/picks")
public class PicksController {
	private final PicksService picksService;

	@GetMapping
	public ResponseEntity<PicksCursorResult> getPickList() {
		return getPickList(Long.MAX_VALUE);
	}

	@GetMapping("/{lastPickId}")
	public ResponseEntity<PicksCursorResult> getPickList(@PathVariable long lastPickId) {
		PicksCursorResult result = picksService.getPickScrollPage(lastPickId);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}
}
