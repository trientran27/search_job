package com.example.searchJob.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.example.searchJob.dto.PageResponse;
import com.example.searchJob.entity.CateJob;

import io.micrometer.core.instrument.util.StringUtils;



@Component
public class PageAndSearchRespository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private static final String LIKE_FORMAT = "%%s%%";
	
	
	public PageResponse<?> searchCateJobByKeyword(int offset, int pageSize, String search){
		List responses = getBySearch(offset, pageSize, CateJob.class, search);
		
		Long totalElements = getTotalElements(CateJob.class, search);
		
		return PageResponse.builder()
					.page(offset)
					.size(pageSize)
					.totalElements(totalElements)
					.contents(responses)
					.build();
	}
	
	
	private List<?> getBySearch(int offset, int pageSize, Class<?> entityClass, String search){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<?> query = criteriaBuilder.createQuery(entityClass);
		Root<?> root = query.from(entityClass);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(search)) {
			Predicate searchPredicate = criteriaBuilder.or(
					criteriaBuilder.like(root.get("name"), String.format(LIKE_FORMAT, search)),
					criteriaBuilder.like(root.get("major"), String.format(LIKE_FORMAT, search))
				);
			predicates.add(searchPredicate);
		}
		
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		return entityManager.createQuery(query)
					.setFirstResult(offset)
					.setMaxResults(pageSize)
					.getResultList();
	}
	
	private Long getTotalElements(Class<?> entityClass, String search){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		Root<?> root = query.from(entityClass);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(search)) {
			Predicate searchPredicate = criteriaBuilder.or(
					criteriaBuilder.like(root.get("name"), String.format(LIKE_FORMAT, search)),
					criteriaBuilder.like(root.get("major"), String.format(LIKE_FORMAT, search))
				);
			predicates.add(searchPredicate);
		}
		
		query.select(criteriaBuilder.count(root));
		query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
		
		return entityManager.createQuery(query).getSingleResult();
	}
}
