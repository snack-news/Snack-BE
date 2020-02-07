package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.RequestQueryDto;

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
	public List<News> findByNewsDto(RequestQueryDto requestQueryDto, LocalDateTime now) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<News> query = builder.createQuery(News.class);

		List<Predicate> criteria = new ArrayList<>();
		Root<News> nr = query.from(News.class);

		if (requestQueryDto.getCategoryId() != null) {
			criteria.add(builder.equal(nr.get("category").get("id"), requestQueryDto.getCategoryId()));
		}

		if (requestQueryDto.getTopicIds() != null) {
			criteria.add(nr.join("topics").get("id").in(requestQueryDto.getTopicIds()));
		}

		if (requestQueryDto.getTagIds() != null) {
			criteria.add(nr.join("tags").get("id").in(requestQueryDto.getTagIds()));
		}

		if (requestQueryDto.getStartDateTime() != null) {
			criteria.add(builder.greaterThanOrEqualTo(nr.get("publishAt"), requestQueryDto.getStartDateTime()));
		}

		if (requestQueryDto.getEndDateTime() != null) {
			criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt"), requestQueryDto.getEndDateTime()));
		}

 		criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt").as(LocalDateTime.class), now));

		if (requestQueryDto.getLastId() != null) {
			criteria.add(builder.lessThan(nr.get("id"), requestQueryDto.getLastId()));
		}

		Predicate[] conditionOfDto = criteria.toArray(new Predicate[0]);

		query.where(builder.and(conditionOfDto))
				.orderBy(builder.desc(nr.get("publishAt")))
				.distinct(true);

		TypedQuery<News> typedQuery = em.createQuery(query);
		if (requestQueryDto.getLimitSize() != 0) {
			typedQuery.setMaxResults(requestQueryDto.getLimitSize());
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<News> findByNewsDto(RequestQueryDto newsDto) {
		return findByNewsDto(newsDto, LocalDateTime.now());
	}
}

