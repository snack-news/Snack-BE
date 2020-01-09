package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.AdminNewsDto;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.AdminService;
import com.snack.news.service.PicksService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("admin/api")
public class AdminController {

	private final long DEFAULT_PAGE_SIZE = 1L;

	private final AdminService adminService;
	private final PicksService picksService;

	@PostMapping("/news")
	public ResponseEntity<NewsDto> createNews(@Valid @RequestBody AdminNewsDto newsDto) {
		return WrappedResponse.ok(adminService.createNews(newsDto));
	}

	@GetMapping("/news")
	public ResponseEntity<Page<News>> getNewsList() {
		return getNewsList(DEFAULT_PAGE_SIZE);
	}

	@GetMapping("/news/{page}")
	public ResponseEntity<Page<News>> getNewsList(@PathVariable long page) {
		Page<News> result = adminService.getNewsList(page);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}

	@PutMapping("/news/{newsId}")
	public ResponseEntity<NewsDto> updateNews(@PathVariable long newsId, @Valid @RequestBody AdminNewsDto newsDto) {
		return WrappedResponse.ok(adminService.updateNews(newsId, newsDto));
	}

	@DeleteMapping("/news/{id}")
	public ResponseEntity<NewsDto> deleteNews(@PathVariable long id) {
		adminService.deleteNews(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/picks")
	public ResponseEntity<Page<Pick>> getPickPage() {
		return getPickPage(DEFAULT_PAGE_SIZE);
	}

	@GetMapping("/picks/{page}")
	public ResponseEntity<Page<Pick>> getPickPage(@PathVariable long page) {
		Page<Pick> result = picksService.getPickPage(page);
		if (result.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return WrappedResponse.ok(result);
	}
}
