package com.ratmirdudin.backendfoodapp.food.repository.dao;

import com.ratmirdudin.backendfoodapp.food.repository.domain.FoodEntity;
import com.ratmirdudin.backendfoodapp.food.models.PageParams;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class FoodCriteriaRepositoryImpl implements FoodCriteriaRepository {
    private final EntityManager em;

    public FoodCriteriaRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Page<FoodEntity> findAllWithFilterTags(PageParams pageParams) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<FoodEntity> cq = cb.createQuery(FoodEntity.class);
        Root<FoodEntity> foodRoot = cq.from(FoodEntity.class);
        foodRoot.fetch("tags", JoinType.LEFT);
        setOrder(pageParams, cq, foodRoot);

        TypedQuery<FoodEntity> typedQuery = em.createQuery(cq);
        typedQuery.setFirstResult((pageParams.getPage() - 1) * pageParams.getLimit());
        typedQuery.setMaxResults(pageParams.getLimit());
        Pageable pageable = getPageable(pageParams);

        long foodCount = getFoodCount();

        List<FoodEntity> foodEntityList = typedQuery.getResultList();

        return new PageImpl<>(foodEntityList, pageable, foodCount);
    }

    private void setOrder(PageParams pageParams,
                          CriteriaQuery<FoodEntity> criteriaQuery,
                          Root<FoodEntity> foodRoot) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        if (pageParams.getSortDir().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(cb.asc(foodRoot.get(pageParams.getSortBy())));
        } else {
            criteriaQuery.orderBy(cb.desc(foodRoot.get(pageParams.getSortBy())));

        }
    }

    private Pageable getPageable(PageParams pageParams) {
        Sort sort = Sort.by(pageParams.getSortDir(), pageParams.getSortBy());
        return PageRequest.of(pageParams.getPage() - 1, pageParams.getLimit(), sort);
    }

    private long getFoodCount() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<FoodEntity> countRoot = countQuery.from(FoodEntity.class);
        countQuery.select(cb.count(countRoot));
        TypedQuery<Long> typedQuery = em.createQuery(countQuery);
        return typedQuery.getResultList().get(0);
    }
}
