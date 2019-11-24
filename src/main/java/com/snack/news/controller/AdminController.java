package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("admin/api")
public class AdminController {

	private final AdminService adminService;

	@PostMapping("/news")
	public ResponseEntity<NewsDto> createNews(@Valid @RequestBody NewsDto newsDto) {
		return WrappedResponse.ok(adminService.createNews(newsDto));
	}

	@GetMapping("/news")
	public ResponseEntity<Page<News>> getNewsList() {
		final long DEFAULT_PAGE_SIZE = 1L;
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
	public ResponseEntity<NewsDto> updateNews(@PathVariable long newsId, @Valid @RequestBody NewsDto newsDto) {
		return WrappedResponse.ok(adminService.updateNews(newsId, newsDto));
	}

	@DeleteMapping("/news/{id}")
	public ResponseEntity<NewsDto> deleteNews(@PathVariable long id) {
		adminService.deleteNews(id);
		return ResponseEntity.ok().build();
	}
}
