package com.lab365.app.pcp.infra.specification;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

/**
 * A generic specification implementation that builds JPA criteria queries
 * based on a collection of search criteria.
 *
 * @param <T> the type of entity this specification works with
 */
public class GenericSpecification<T> implements Specification<T> {
    private final List<SearchCriteria> criteriaList;

    /**
     * Constructs an empty specification.
     */
    public GenericSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    /**
     * Adds a search criterion to this specification.
     *
     * @param criteria the search criterion to add (must not be null)
     * @throws NullPointerException if the criteria is null
     */
    public void add(SearchCriteria criteria) {
        criteriaList.add(Objects.requireNonNull(criteria, "Criteria cannot be null"));
    }

    /**
     * Converts the specification into a JPA predicate.
     *
     * @param root    the root entity in the from clause
     * @param query   the criteria query being built (may be null)
     * @param builder the criteria builder used to construct predicates
     * @return the predicate representing this specification, or null if no criteria
     *         are defined
     * @throws IllegalArgumentException if any criteria references an invalid
     *                                  property
     */
    @Override
    @Nullable
    public Predicate toPredicate(@NonNull Root<T> root,
            @Nullable CriteriaQuery<?> query,
            @NonNull CriteriaBuilder builder) {
        if (criteriaList.isEmpty()) {
            return null;
        }

        List<Predicate> predicates = new ArrayList<>(criteriaList.size());

        for (SearchCriteria criteria : criteriaList) {
            try {
                Class<?> attributeType = root.get(criteria.getKey()).getJavaType();
                Object value = processValue(criteria, attributeType);

                if (value == null) {
                    continue;
                }

                Predicate predicate = createPredicate(root, builder, criteria, value);
                if (predicate != null) {
                    predicates.add(predicate);
                }
            } catch (IllegalArgumentException e) {
                continue;
            }
        }

        return predicates.isEmpty() ? null : builder.and(predicates.toArray(new Predicate[0]));
    }

    /**
     * Processes the criteria value according to its expected type.
     *
     * @param criteria      the search criteria containing the value
     * @param attributeType the expected type of the attribute
     * @return the processed value, or null if the value cannot be converted
     */
    @Nullable
    @SuppressWarnings("unchecked")
    private Object processValue(SearchCriteria criteria, Class<?> attributeType) {
        Object value = criteria.getValue();

        if (attributeType.isEnum() && value instanceof String stringValue) {
            try {
                return Enum.valueOf((Class<Enum>) attributeType, stringValue);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        return value;
    }

    /**
     * Creates a predicate for the given criteria and value.
     *
     * @param root     the root entity
     * @param builder  the criteria builder
     * @param criteria the search criteria
     * @param value    the processed value
     * @return the created predicate, or null if the operation is not supported
     */
    @Nullable
    private Predicate createPredicate(Root<T> root, CriteriaBuilder builder,
            SearchCriteria criteria, Object value) {
        return switch (criteria.getOperation()) {
            case EQUALS -> builder.equal(root.get(criteria.getKey()), value);
            case CONTAINS -> builder.like(
                    builder.lower(root.get(criteria.getKey())),
                    "%" + ((String) value).toLowerCase() + "%");
            case GREATER_THAN -> builder.greaterThan(
                    root.get(criteria.getKey()),
                    value.toString());
            case LESS_THAN -> builder.lessThan(
                    root.get(criteria.getKey()),
                    value.toString());
            case IN -> root.get(criteria.getKey()).in(value);
            default -> null;
        };
    }
}