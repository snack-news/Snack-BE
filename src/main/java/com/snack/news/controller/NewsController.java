package com.snack.news.controller;

import com.snack.news.domain.news.News;
import com.snack.news.dto.*;
import com.snack.news.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final NewsService newsService;

	@GetMapping
	public WrappedResponse<List<News>> getNewsList(@ModelAttribute @Valid RequestNewsDto newsDto) {
		ListCursorResult<News> result = newsService.getNewsList(newsDto);
		if (result.isEmpty()) {
			return WrappedResponse.noContents();
		}
		return WrappedResponse.ok(result);
	}

	@GetMapping("/{newsId}")
	public WrappedResponse<News> getNews(@PathVariable Long newsId) {
		return WrappedResponse.ok(Wrapper.valueOf(newsService.getNews(newsId)));
	}
}