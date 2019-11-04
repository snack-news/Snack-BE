package com.snack.news.service;

import com.snack.news.domain.tag.Tag;
import com.snack.news.dto.TagDto;
import com.snack.news.exception.TagNotFoundException;
import com.snack.news.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TagService {
	private final TagRepository tagRepository;


	@Transactional
	public Tag createTag(TagDto tagDto) {
		Tag tag = tagDto.getNewEntity();

		try {
			tagRepository.save(tag);
		} catch (DataIntegrityViolationException e) {
			throw new TagNotFoundException();
		}

		return tag;
	}

	@Transactional
	public List<Tag> getTagList(List<Long> tagIds) {
		if (Objects.isNull(tagIds)) {
			return Collections.emptyList();
		}

		return tagRepository.findByIdIn(tagIds);
	}

	@Transactional
	public List<Tag> getAllTagList() {
		return tagRepository.findAll();
	}

	@Transactional
	public Tag updateTag(TagDto tagDto) {
		Tag tag = tagDto.getUpdateEntity();
		tagRepository.findById(tag.getId()).orElseThrow(TagNotFoundException::new);

		return tagRepository.save(tag);
	}
}