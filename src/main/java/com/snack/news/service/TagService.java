package com.snack.news.service;

import com.snack.news.domain.Tag;
import com.snack.news.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class TagService {
	private final TagRepository tagRepository;

	public List<Tag> getTagList(List<Long> tagIds) {
		if (Objects.isNull(tagIds)) {
			return Collections.emptyList();
		}

		return tagRepository.findByIdIn(tagIds);
	}

}
