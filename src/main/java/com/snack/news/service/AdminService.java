package com.snack.news.service;

import com.snack.news.domain.category.Category;
import com.snack.news.domain.news.News;
import com.snack.news.domain.picks.Pick;
import com.snack.news.domain.tag.Tag;
import com.snack.news.domain.topic.Topic;
import com.snack.news.dto.AdminNewsDto;
import com.snack.news.dto.PickDto;
import com.snack.news.exception.NewsNotFoundException;
import com.snack.news.exception.PicksNotFoundException;
import com.snack.news.repository.NewsRepository;
import com.snack.news.repository.PicksRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class AdminService {
	private final static Sort SORT_BY_ID = Sort.by(Sort.Direction.DESC, "id");
	private final static int DEFAULT_PAGING_SIZE = 10;

	private final NewsRepository newsRepository;
	private final PicksRepository picksRepository;
	private final CategoryService categoryService;
	private final TopicService topicService;
	private final TagService tagService;

	@Transactional
	public AdminNewsDto createNews(AdminNewsDto newsDto) {
		News news = generateNews(newsDto);
		newsRepository.save(news);

		return AdminNewsDto.builder().id(news.getId()).build();
	}

	public Page<News> getNewsList(long page) {
		Pageable pageable = PageRequest.of((int) page - 1, DEFAULT_PAGING_SIZE, SORT_BY_ID);
		return newsRepository.findAll(pageable);
	}

	@Transactional
	public AdminNewsDto updateNews(long newsId, AdminNewsDto newsDto) {
		News originNews = newsRepository.findById(newsId).orElseThrow(NewsNotFoundException::new);
		News updatedNews = updateNews(originNews, newsDto);

		newsRepository.save(updatedNews);

		return AdminNewsDto.builder().id(newsId).build();
	}

	@Transactional
	public void deleteNews(long newsId) {
		try {
			newsRepository.deleteById(newsId);
		} catch (IllegalArgumentException e) {
			log.error("Invalid News Id in DeleteNews : {}", newsId, e);
			throw new NewsNotFoundException();
		}
	}

	private News generateNews(AdminNewsDto newsDto) {
		Category category = categoryService.getCategory(newsDto.getCategoryId());
		List<Topic> topics = topicService.getTopicList(newsDto.getTopicNames());
		List<Tag> tags = tagService.getTagList(newsDto.getTagIds());

		return newsDto.toEntity(category, topics, tags);
	}

	private News updateNews(News news, AdminNewsDto newsDto) {
		Category category = categoryService.getCategory(newsDto.getCategoryId());
		List<Topic> topics = topicService.getTopicList(newsDto.getTopicNames());
		List<Tag> tags = tagService.getTagList(newsDto.getTagIds());

		return news.updateNews(
				newsDto.getTitle(),
				newsDto.getContent(),
				newsDto.getLink(),
				newsDto.getPublishAt(),
				category,
				topics,
				tags);
	}

	@Transactional
	public List<PickDto> createPick(List<PickDto> pickDtoList) {
		List<PickDto> createPickResult = new ArrayList<>();

		for(PickDto pickDto : pickDtoList) {
			Pick pick = generatePick(pickDto);
			picksRepository.save(pick);
			createPickResult.add(PickDto.builder().id(pick.getId()).build());
		}

		return createPickResult;
	}

	private Pick generatePick(PickDto pickDto) {
		return pickDto.toEntity(
				categoryService.getCategory(pickDto.getCategoryId()),
				topicService.getTopicList(pickDto.getTopicNames())
		);
	}

	public Page<Pick> getPickPage(int page) {
		Pageable pageable = PageRequest.of(page - 1, DEFAULT_PAGING_SIZE, SORT_BY_ID);
		return picksRepository.findAll(pageable);
	}

	@Transactional
	public void deletePicks(long picksId) {
		try {
			picksRepository.deleteById(picksId);
		} catch (IllegalArgumentException e) {
			log.error("Invalid News Id in DeleteNews : {}", picksId, e);
			throw new NewsNotFoundException();
		}
	}

	@Transactional
	public PickDto updatePicks(long picksId, PickDto pickDto) {
		Pick originNews = picksRepository.findById(picksId).orElseThrow(PicksNotFoundException::new);
		Pick updatedNews = updatePicks(originNews, pickDto);

		picksRepository.save(updatedNews);

		return PickDto.builder().id(picksId).build();
	}

	private Pick updatePicks(Pick picks, PickDto pickDto) {
		return picks.updatePicks(
				pickDto.getLink(),
				pickDto.getPublishAt(),
				categoryService.getCategory(pickDto.getCategoryId()),
				topicService.getTopicList(pickDto.getTopicNames()));
	}


}