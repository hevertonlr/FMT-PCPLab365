package com.lab365.app.pcp.infra.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecification<T> implements Specification<T> {
    private final List<SearchCriteria> criteriaList;

    public GenericSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteriaList) {
            Class<?> attributeType;
            try {
                attributeType = root.get(criteria.getKey()).getJavaType();
            } catch (IllegalArgumentException e) {
                continue;
            }

            Object value = criteria.getValue();

            if (attributeType.isEnum() && value instanceof String) {
                try {
                    value = Enum.valueOf((Class<Enum>) attributeType, (String) value);
                } catch (IllegalArgumentException e) {
                    continue;
                }
            }

            switch (criteria.getOperation()) {
                case EQUALS:
                    predicates.add(builder.equal(root.get(criteria.getKey()), value));
                    break;
                case CONTAINS:
                    predicates.add(builder.like(builder.lower(root.get(criteria.getKey())),
                            "%" + ((String) value).toLowerCase() + "%"));
                    break;
                case GREATER_THAN:
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()), value.toString()));
                    break;
                case LESS_THAN:
                    predicates.add(builder.lessThan(root.get(criteria.getKey()), value.toString()));
                    break;
                case IN:
                    predicates.add(root.get(criteria.getKey()).in(value));
                    break;
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
