package com.snack.news.repository;

import com.snack.news.domain.News;
import com.snack.news.domain.Topic;
import com.snack.news.dto.NewsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class NewsRepositoryImpl implements NewsRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<News> findByNewsDto(NewsDto newsDto) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<News> query = builder.createQuery(News.class);

		Root<News> nr = query.from(News.class);
		Join<News, Topic> ntj = nr.join("topics");

		query.multiselect(nr, ntj)
				.distinct(true)
				.select(nr)
				.where(ntj.get("id").in(newsDto.getTopicIds()));

		/*for (Long id : newsDto.getTopicIds()) {
			cq.where(builder.equal(nr.get("id"), id));
			 cq.where(builder.in(nr.get("id")).in(newsDto.getTopicIds()));
		}*/

		// Predicate periodQuery = builder.between(nr.get("createAt"), newsDto.getStartDateTime(), newsDto.getEndDateTime());

		TypedQuery<News> typeQuery = em.createQuery(query);
		List<News> newsList = typeQuery.getResultList();
		return newsList;
	}
}
