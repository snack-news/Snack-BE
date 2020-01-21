package com.snack.news.repository;

import com.snack.news.domain.news.News;
import com.snack.news.dto.RequestNewsDto;

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
	public List<News> findByNewsDto(RequestNewsDto requestNewsDto, LocalDateTime now) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<News> query = builder.createQuery(News.class);

		List<Predicate> criteria = new ArrayList<>();
		Root<News> nr = query.from(News.class);

		if (requestNewsDto.getCategoryId() != null) {
			criteria.add(builder.equal(nr.get("category").get("id"), requestNewsDto.getCategoryId()));
		}

		if (requestNewsDto.getTopicIds() != null) {
			criteria.add(nr.join("topics").get("id").in(requestNewsDto.getTopicIds()));
		}

		if (requestNewsDto.getTagIds() != null) {
			criteria.add(nr.join("tags").get("id").in(requestNewsDto.getTagIds()));
		}

		if (requestNewsDto.getStartDateTime() != null) {
			criteria.add(builder.greaterThanOrEqualTo(nr.get("publishAt"), requestNewsDto.getStartDateTime()));
		}

		if (requestNewsDto.getEndDateTime() != null) {
			criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt"), requestNewsDto.getEndDateTime()));
		}

		criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt").as(LocalDateTime.class), now));

		if(requestNewsDto.getLastNewsId() != null) {
			criteria.add(builder.lessThan(nr.get("id"), requestNewsDto.getLastNewsId()));
		}

		Predicate[] conditionOfDto = criteria.toArray(new Predicate[0]);

		query.where(builder.and(conditionOfDto))
				.orderBy(builder.desc(nr.get("publishAt")))
				.distinct(true);

		TypedQuery<News> typedQuery = em.createQuery(query);
		/*if(requestNewsDto.getLimitSize() != 0) {
			typedQuery.setMaxResults(requestNewsDto.getLimitSize());
		}*/

		return typedQuery.getResultList();
	}

	@Override
	public List<News> findByNewsDto(RequestNewsDto newsDto) {
		return findByNewsDto(newsDto, LocalDateTime.now());
	}
}

