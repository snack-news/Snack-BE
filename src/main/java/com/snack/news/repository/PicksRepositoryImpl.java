package com.snack.news.repository;

import com.snack.news.domain.picks.Pick;
import com.snack.news.dto.RequestInquiryDto;

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

public class PicksRepositoryImpl implements PicksRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


 	@Override
	public List<Pick> findByPickDto(RequestInquiryDto requestPostDto, LocalDateTime now) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Pick> query = builder.createQuery(Pick.class);

		List<Predicate> criteria = new ArrayList<>();
		Root<Pick> nr = query.from(Pick.class);

		if (requestPostDto.getCategoryId() != null) {
			criteria.add(builder.equal(nr.get("category").get("id"), requestPostDto.getCategoryId()));
		}

		if (requestPostDto.getTopicIds() != null) {
			criteria.add(nr.join("topics").get("id").in(requestPostDto.getTopicIds()));
		}

		if (requestPostDto.getTagIds() != null) {
			criteria.add(nr.join("tags").get("id").in(requestPostDto.getTagIds()));
		}

		if (requestPostDto.getStartDateTime() != null) {
			criteria.add(builder.greaterThanOrEqualTo(nr.get("publishAt"), requestPostDto.getStartDateTime()));
		}

		if (requestPostDto.getEndDateTime() != null) {
			criteria.add(builder.lessThan(nr.get("publishAt"), requestPostDto.getEndDateTime()));
		}

		criteria.add(builder.lessThanOrEqualTo(nr.get("publishAt").as(LocalDateTime.class), now));

		Predicate[] conditionOfDto = criteria.toArray(new Predicate[0]);

		query.where(builder.and(conditionOfDto))
				.orderBy(builder.desc(nr.get("publishAt")))
				.distinct(true);

		TypedQuery<Pick> typedQuery = em.createQuery(query);
		if(requestPostDto.getLimitSize() != 0) {
			typedQuery.setMaxResults(requestPostDto.getLimitSize());
		}

		return typedQuery.getResultList();
	}

	@Override
	public List<Pick> findByPickDto(RequestInquiryDto requestPickDto) {
		return findByPickDto(requestPickDto, LocalDateTime.now());
	}
}

