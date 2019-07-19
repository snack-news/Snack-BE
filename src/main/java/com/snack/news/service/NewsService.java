package com.snack.news.service;

import com.snack.news.domain.Category;
import com.snack.news.domain.News;
import com.snack.news.domain.Tag;
import com.snack.news.domain.Topic;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.base.NotFoundException;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;
	private final CategoryService categoryService;
	private final TopicService topicService;
	private final TagService tagService;

	@Transactional
	public NewsDto createNews(NewsDto newsDto) throws NotFoundException {
		Category category = categoryService.getCategory(newsDto.getCategoryId());
		List<Topic> topics = topicService.getTopicList(newsDto.getTopicIds());
		List<Tag> tags = tagService.getTagList(newsDto.getTagIds());

		News news = newsDto.toEntity(category, topics, tags);
		newsRepository.save(news);

		return NewsDto.builder().id(news.getId()).build();
	}

	public List<News> getAllNewsList() {
		return newsRepository.findAll();
	}

	public List<News> getNewsList(NewsDto newsDto) {
		return newsRepository.findByNewsDto(newsDto);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}