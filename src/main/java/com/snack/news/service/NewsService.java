package com.snack.news.service;

import com.snack.news.domain.category.Category;
import com.snack.news.domain.news.News;
import com.snack.news.domain.tag.Tag;
import com.snack.news.domain.topic.Topic;
import com.snack.news.dto.NewsDto;
import com.snack.news.dto.Period;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;
	private final CategoryService categoryService;
	private final TopicService topicService;
	private final TagService tagService;

	@Transactional
	public NewsDto createNews(NewsDto newsDto) {
		Category category = categoryService.getCategory(newsDto.getCategoryId());
		List<Topic> topics = topicService.getTopicList(newsDto.getTopicIds());
		List<Tag> tags = tagService.getTagList(newsDto.getTagIds());

		News news = newsDto.toEntity(category, topics, tags);
		newsRepository.save(news);

		return NewsDto.builder().id(news.getId()).build();
	}

	public List<News> getNewsListForUserView(NewsDto newsDto) {
		new Period(newsDto.getStartDateTime(), newsDto.getEndDateTime()).validationCheck();
		return newsRepository.findByNewsDto(newsDto);
	}

	public Page<News> getNewsListForAdmin(int page) {
		Pageable pageable = PageRequest.of(page - 1, 10);
		return newsRepository.findAll(pageable);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}