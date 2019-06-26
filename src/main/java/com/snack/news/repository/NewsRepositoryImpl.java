package com.snack.news.repository;

import com.snack.news.domain.News;
import com.snack.news.domain.Topic;
import com.snack.news.dto.NewsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<News> findByNewsDto(NewsDto newsDto) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<News> query = builder.createQuery(News.class);

		Root<News> m = query.from(News.class);
		Join<News, Topic> join = m.join("topics");
		query.select(m).distinct(true);

		Predicate periodQuery = builder.between(m.get("createAt"), newsDto.getStartDateTime(), newsDto.getEndDateTime());
		builder.equal(m.get("topics"), newsDto.getTopicIds());
		query.select(m)
				.distinct(true)
				.where(periodQuery);


		TypedQuery<News> typeQuery = em.createQuery(query);
		List<News> newsList = typeQuery.getResultList();


		return newsList;
	}
}
