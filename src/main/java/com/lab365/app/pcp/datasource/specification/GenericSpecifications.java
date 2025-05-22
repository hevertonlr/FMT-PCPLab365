package com.lab365.app.pcp.datasource.specification;

import jakarta.persistence.criteria.Fetch;
import jakarta.persistence.criteria.JoinType;

import java.util.Set;

import org.springframework.data.jpa.domain.Specification;

public class GenericSpecifications {
    public static <T> Specification<T> withRelations(Set<String> relations) {
        return (root, query, cb) -> {
            if (query != null && query.getResultType() != Long.class && relations != null) {
                relations.forEach(relation -> {
                    String[] paths = relation.split("\\.");
                    Fetch<?, ?> fetch = null;
                    for (String path : paths) {
                        fetch = fetch == null ? root.fetch(path, JoinType.LEFT) : fetch.fetch(path, JoinType.LEFT);
                    }
                });
            }
            return null;
        };
    }
}
