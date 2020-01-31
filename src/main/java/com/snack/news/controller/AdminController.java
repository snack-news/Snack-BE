package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.AdminNewsDto;
import com.snack.news.dto.WrappedResponse;
import com.snack.news.dto.Wrapper;
import com.snack.news.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("admin/api")
public class AdminController {

	private final AdminService adminService;

	@PostMapping("/news")
	public WrappedResponse<AdminNewsDto> createNews(@Valid @RequestBody AdminNewsDto newsDto) {
		return WrappedResponse.ok(Wrapper.valueOf(adminService.createNews(newsDto)));
	}

	@GetMapping("/news")
	public WrappedResponse<Page<News>> getNewsList() {
		final long DEFAULT_PAGE_SIZE = 1L;
		return getNewsList(DEFAULT_PAGE_SIZE);
	}

	@GetMapping("/news/{page}")
	public WrappedResponse<Page<News>> getNewsList(@PathVariable long page) {
		Page<News> result = adminService.getNewsList(page);
		if (result.isEmpty()) {
			return WrappedResponse.noContents();
		}
		return WrappedResponse.ok(Wrapper.valueOf(result));
	}

	@PutMapping("/news/{newsId}")
	public WrappedResponse<AdminNewsDto> updateNews(@PathVariable long newsId, @Valid @RequestBody AdminNewsDto newsDto) {
		return WrappedResponse.ok(Wrapper.valueOf(adminService.updateNews(newsId, newsDto)));
	}

	@DeleteMapping("/news/{id}")
	public WrappedResponse<AdminNewsDto> deleteNews(@PathVariable long id) {
		adminService.deleteNews(id);
		return WrappedResponse.okEmpty();
	}
}
