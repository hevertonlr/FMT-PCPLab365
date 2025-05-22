package com.lab365.app.pcp.infra.utils;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab365.app.pcp.controller.dto.request.PageableParams;
import com.lab365.app.pcp.infra.specification.GenericSpecification;
import com.lab365.app.pcp.infra.specification.SearchCriteria;
import com.lab365.app.pcp.infra.specification.SearchCriteria.SearchOperation;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Util {
    public static String toJSON(Object obj) {
        try {
            return new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getPathMethod() {
        return (ServletUriComponentsBuilder.fromCurrentRequest()).buildAndExpand().getPath();
    }

    public static Jwt getTokenJwt() {
        return (Jwt) SecurityContextHolder.getContext()
                .getAuthentication()
                .getCredentials();
    }

    public static String getTokenFromRequest() {
        return getTokenJwt().getTokenValue();
    }

    public static Long getUserIdFromToken() {
        return Long.valueOf(getTokenJwt().getSubject());
    }

    public static String getRoleFromToken() {
        return getTokenJwt().getClaimAsString("scope").toUpperCase();
    }

    public static <T> EntityGraph<T> fromAttributePaths(
            EntityManager em,
            Class<T> entityClass,
            String... attributePaths) {

        EntityGraph<T> entityGraph = em.createEntityGraph(entityClass);
        for (String path : attributePaths) {
            entityGraph.addAttributeNodes(path);
        }
        return entityGraph;
    }

    /**
     * Parse comma-separated relations string into a set of TeacherRelation enums
     * 
     * @param relations Comma-separated list of relations
     * @return Set of TeacherRelation enums
     */

    public static <T extends Enum<T>> Set<T> parseRelations(String relations, Class<T> enumClass) {
        if (relations == null || relations.isEmpty()) {
            return EnumSet.noneOf(enumClass);
        }

        return Arrays.stream(relations.split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .filter(rel -> !rel.isEmpty())
                .map(rel -> Enum.valueOf(enumClass, rel))
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(enumClass)));
    }

    public static Pageable buildPageable(PageableParams params) {
        return params.getSort() != null
                ? PageRequest.of(params.getPage(), params.getSize(), parseSort(params.getSort()))
                : PageRequest.of(params.getPage(), params.getSize());
    }

    private static Sort parseSort(String sort) {
        String[] parts = sort.split(",");
        return Sort.by(
                parts.length > 1 && "desc".equalsIgnoreCase(parts[1]) ? Sort.Direction.DESC : Sort.Direction.ASC,
                parts[0]);
    }

    public static <T> Specification<T> buildSpec(Map<String, String> filters) {
        GenericSpecification<T> spec = new GenericSpecification<>();
        filters.forEach((key, value) -> addCriteria(spec, key, value));
        return spec;
    }

    private static <T> void addCriteria(GenericSpecification<T> spec, String key, String value) {
        if (key.contains(".")) {
            String[] parts = key.split("\\.");
            if (parts.length == 2) {
                SearchCriteria.SearchOperation operation = SearchCriteria.SearchOperation.fromString(parts[1]);
                if (operation != null) {
                    spec.add(new SearchCriteria(parts[0], operation, value));
                }
            }
        } else {
            spec.add(new SearchCriteria(key, SearchOperation.EQUALS, value));
        }
    }

}
