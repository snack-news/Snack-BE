package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.RequestQueryDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PicksRepositoryImpl implements PicksRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


 	@Override
	public List<Pick> findByPickDto(RequestQueryDto requestQueryDto, LocalDateTime now) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pick> query = builder.createQuery(Pick.class);

		List<Predicate> criteria = new ArrayList<>();
		Root<Pick> nr = query.from(Pick.class);

		if (requestQueryDto.getCategoryId() != null) {
			criteria.add(builder.equal(nr.get("category").get("id"), requestQueryDto.getCategoryId()));
		}

		if (requestQueryDto.getTopicIds() != null) {
			criteria.add(nr.join("topics").get("id").in(requestQueryDto.getTopicIds()));
		}

		Path<LocalDateTime> publishAtPath = nr.get("publishAt");

		if (requestQueryDto.getStartDateTime() != null) {
			criteria.add(builder.greaterThanOrEqualTo(nr.get("publishAt"), requestQueryDto.getStartDateTime()));
			criteria.add(builder.greaterThanOrEqualTo(publishAtPath, requestQueryDto.getStartDateTime()));
		}

		if (requestQueryDto.getEndDateTime() != null) {
			criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt"), requestQueryDto.getEndDateTime()));
			Predicate publishAtBeforeThenEndDate = builder.lessThan(publishAtPath, requestQueryDto.getEndDateTime());
			Predicate p2 = builder.and(builder.equal(publishAtPath, requestQueryDto.getEndDateTime()), builder.lessThan(nr.get("id"), requestQueryDto.getLastId()));

			criteria.add(builder.or(publishAtBeforeThenEndDate, p2));
		}

		criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt").as(LocalDateTime.class), now));

		Predicate[] conditionOfDto = criteria.toArray(new Predicate[0]);

		query.where(builder.and(conditionOfDto))
				.orderBy(builder.desc(nr.get("publishAt")))
				.orderBy(builder.desc(nr.get("publishAt")), builder.desc(nr.get("id")))
				.distinct(true);

		TypedQuery<Pick> typedQuery = em.createQuery(query);
		if(requestQueryDto.getLimitSize() != 0) {
			typedQuery.setMaxResults(requestQueryDto.getLimitSize());
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Pick> findByPickDto(RequestQueryDto requestPickDto) {
		return findByPickDto(requestPickDto, LocalDateTime.now());
	}
}

