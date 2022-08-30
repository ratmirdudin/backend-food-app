package com.ratmirdudin.backendfoodapp.repositories;

import com.ratmirdudin.backendfoodapp.models.Food;
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
    public Page<Food> findAllWithFilterTags(PageParams pageParams) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Food> cq = cb.createQuery(Food.class);
        Root<Food> foodRoot = cq.from(Food.class);
        foodRoot.fetch("tags", JoinType.LEFT);
        setOrder(pageParams, cq, foodRoot);

        TypedQuery<Food> typedQuery = em.createQuery(cq);
        typedQuery.setFirstResult((pageParams.getPage() - 1) * pageParams.getLimit());
        typedQuery.setMaxResults(pageParams.getLimit());
        Pageable pageable = getPageable(pageParams);

        long foodCount = getFoodCount();

        List<Food> foodList = typedQuery.getResultList();

        return new PageImpl<>(foodList, pageable, foodCount);
    }

    private void setOrder(PageParams pageParams,
                          CriteriaQuery<Food> criteriaQuery,
                          Root<Food> foodRoot) {
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
        Root<Food> countRoot = countQuery.from(Food.class);
        countQuery.select(cb.count(countRoot));
        TypedQuery<Long> typedQuery = em.createQuery(countQuery);
        return typedQuery.getResultList().get(0);
    }
}
