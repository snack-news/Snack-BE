package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<News> findByNewsDto(NewsDto newsDto, LocalDateTime now) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<News> query = builder.createQuery(News.class);

		List<Predicate> criteria = new ArrayList<>();
		Root<News> nr = query.from(News.class);

		if (newsDto.getCategoryId() != null) {
			criteria.add(builder.equal(nr.get("category").get("id"), newsDto.getCategoryId()));
		}

		if (newsDto.getTopicIds() != null) {
			criteria.add(nr.join("topics").get("id").in(newsDto.getTopicIds()));
		}

		if (newsDto.getTagIds() != null) {
			criteria.add(nr.join("tags").get("id").in(newsDto.getTagIds()));
		}

		if (newsDto.getStartDateTime() != null) {
			criteria.add(builder.greaterThanOrEqualTo(nr.get("publishAt"), newsDto.getStartDateTime()));
		}

		if (newsDto.getEndDateTime() != null) {
			criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt"), newsDto.getEndDateTime()));
		}

		if (newsDto.getPublishAt() != null) {
			criteria.add(builder.greaterThanOrEqualTo(nr.get("publishAt").as(LocalDateTime.class), now));
		}

		if(newsDto.getId() != null) {
			criteria.add(builder.lessThan(nr.get("id"), newsDto.getId()));
		}

		Predicate[] conditionOfDto = criteria.toArray(new Predicate[0]);

		query.where(builder.and(conditionOfDto))
				.orderBy(builder.desc(nr.get("publishAt")))
				.distinct(true);

		TypedQuery<News> typedQuery = em.createQuery(query);
		if(newsDto.getLimitSize() != 0) {
			typedQuery.setMaxResults(newsDto.getLimitSize());
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<News> findByNewsDto(NewsDto newsDto) {
		return findByNewsDto(newsDto, LocalDateTime.now());
	}
}