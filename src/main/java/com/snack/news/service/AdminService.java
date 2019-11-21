package com.snack.news.service;

import com.snack.news.domain.category.Category;
import com.snack.news.domain.news.News;
import com.snack.news.domain.tag.Tag;
import com.snack.news.domain.topic.Topic;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class AdminService {
	private final static Sort SORT_BY_ID = Sort.by(Sort.Direction.DESC, "id");

	private final NewsRepository newsRepository;
	private final CategoryService categoryService;
	private final TopicService topicService;
	private final TagService tagService;

	@Transactional
	public NewsDto createNews(NewsDto newsDto) {
		News news = generateNews(newsDto);
		newsRepository.save(news);

		return NewsDto.builder().id(news.getId()).build();
	}

	public Page<News> getNewsList(long page) {
		Pageable pageable = PageRequest.of((int) page - 1, 10);
		return newsRepository.findAll(pageable);
	}

	@Transactional
	public NewsDto updateNews(long newsId, NewsDto newsDto) {
		if (!newsRepository.existsById(newsId)) {
			throw new NewsNotFoundException();
		}

		News news = generateNews(newsDto);
		news.setId(newsId);

		newsRepository.save(news);

		return NewsDto.builder().id(news.getId()).build();
	}

	@Transactional
	public void deleteNews(long newsId) {
		try {
			newsRepository.deleteById(newsId);
		} catch (IllegalArgumentException e) {
			throw new NewsNotFoundException();
		}
	}

	private News generateNews(NewsDto newsDto) {
		Category category = categoryService.getCategory(newsDto.getCategoryId());
		List<Topic> topics = topicService.getTopicList(newsDto.getTopicIds());
		List<Tag> tags = tagService.getTagList(newsDto.getTagIds());

		return newsDto.toEntity(category, topics, tags);
	}
}