package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PageableParams {
    @Min(0)
    private int page = 0;
    @Min(1)
    @Max(100)
    private int size = 10;
    private String sort;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    private final Map<String, String> filters = new HashMap<>();

    @JsonAnySetter
    public void addFilter(String key, String value) {
        if (!isPagingOrSortParam(key)) {
            filters.put(key, value);
        }
    }

    public Map<String, String> getFilters() {
        return Collections.unmodifiableMap(filters);
    }

    private boolean isPagingOrSortParam(String key) {
        return key.equals("page") || key.equals("size") || key.equals("sort");
    }
}
