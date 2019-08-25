package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.NewsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<News> findByNewsDto(NewsDto newsDto) {
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
			criteria.add(builder.greaterThan(nr.get("createAt"), newsDto.getStartDateTime()));
		}

		if (newsDto.getEndDateTime() != null) {
			criteria.add(builder.lessThan(nr.get("createAt"), newsDto.getEndDateTime()));
		}

		query.where(builder.and(criteria.toArray(new Predicate[0]))).distinct(true);

		return em.createQuery(query).getResultList();
	}
}