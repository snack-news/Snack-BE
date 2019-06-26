package com.snack.news.service;

import com.snack.news.domain.News;
import com.snack.news.domain.Topic;
import com.snack.news.dto.NewsDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.repository.NewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class NewsService {

	private final NewsRepository newsRepository;
	private final TopicService topicService;

	@Transactional
	public NewsDto createNews(NewsDto newsDto) {
		List<Topic> topics = topicService.getTopicList(newsDto.getTopicIds());
		News news = newsDto.toEntity(topics);

		newsRepository.save(news);

		return NewsDto.builder().id(news.getId()).build();
	}

	public List<News> getNewsList(NewsDto newsDto) {
		LocalDateTime startTime = newsDto.getStartDateTime();
		LocalDateTime endTime = newsDto.getEndDateTime();

		return newsRepository.findByCreateAtBetween(startTime, endTime);
	}

	public List<News> getNewsList() {
		return newsRepository.findAll();
	}

	public List<News> getTopicNewsList(NewsDto newsDto) {
		return newsRepository.findByTopicsIdIn(newsDto.getTopicIds());
	}

	public List<News> getTopicNewsListCriteria(NewsDto newsDto) {
		return newsRepository.findByNewsDto(newsDto);
	}

	public News getNews(Long newsId) {
		return newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
	}
}