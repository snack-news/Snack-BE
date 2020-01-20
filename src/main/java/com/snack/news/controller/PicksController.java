package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.PicksService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/picks")
public class PicksController {

	private final PicksService picksService;

	@GetMapping("/news")
	public ResponseEntity<Page<Pick>> getNewsList() {
		return getNewsList(DEFAULT_PAGE_SIZE);
	}

	@GetMapping("/news/{page}")
	public ResponseEntity<Page<Pick>> getNewsList(@PathVariable long page) {
		Page<News> result = adminService.getNewsList(page);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}
}
