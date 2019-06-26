package com.snack.news.repository;

import com.snack.news.domain.News;
import com.snack.news.domain.Topic;
import com.snack.news.dto.NewsDto;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
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

		if (newsDto.getTopicIds() != null) {
			criteria.add(nr.join("topics").get("id").in(newsDto.getTopicIds()));
		}
		if (newsDto.getStartDateTime() != null) {
			criteria.add(builder.greaterThan(nr.get("createAt"), newsDto.getStartDateTime()));
		}
		if (newsDto.getEndDateTime() != null) {
			criteria.add(builder.lessThan(nr.get("createAt"), newsDto.getEndDateTime()));
		}

		query.where(builder.and(criteria.toArray(new Predicate[0]))).distinct(true);

		TypedQuery<News> typeQuery = em.createQuery(query);

		return typeQuery.getResultList();
	}
}