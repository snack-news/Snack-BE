package com.snack.news.dto;

import com.snack.news.domain.*;
import com.snack.news.exception.NewsBadRequestException;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewsDto {

	private Long id;
	@NotNull(message = "News title")
	private String title;
	@NotNull(message = "News content")
	private String content;
	private String link;
	@NotNull(message = "Category ID")
	private Long categoryId;
	private TopicType type;
	private List<Long> topicIds;
	private List<Long> tagIds;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime startDateTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime endDateTime;

	public void dateValidationCheck() {
		if (Objects.isNull(startDateTime) || Objects.isNull(endDateTime)) {
			throw new NewsBadRequestException();
		}

		if (!startDateTime.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
			throw new NewsBadRequestException();
		}

		if (!isBothDatesInOneWeek(startDateTime, endDateTime) || !isBothDatesInSameMonth(startDateTime, endDateTime)) {
			throw new NewsBadRequestException();
		}
	}

	private boolean isBothDatesInOneWeek(LocalDateTime start, LocalDateTime end) {
		return end.minusWeeks(1L).isBefore(start);
	}

	private boolean isBothDatesInSameMonth(LocalDateTime start, LocalDateTime end) {
		return end.getMonth() == start.getMonth();
	}

	public News toEntity(Category category, List<Topic> topics, List<Tag> tags) {
		return News.builder()
				.title(title)
				.content(content)
				.category(category)
				.topics(topics)
				.tags(tags)
				.link(link)
				.build();
	}
}