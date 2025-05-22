package com.lab365.app.pcp.infra.specification;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public enum SearchOperation {
        EQUALS, CONTAINS, GREATER_THAN, LESS_THAN, IN;

        private static final Map<String, SearchOperation> lookup = new HashMap<>();

        static {
            for (SearchOperation op : values()) {
                lookup.put(op.name().toLowerCase(), op);
            }

            lookup.put("contains", CONTAINS);
            lookup.put("eq", EQUALS);
            lookup.put("gt", GREATER_THAN);
            lookup.put("lt", LESS_THAN);
        }

        public static SearchOperation fromString(String input) {
            return (input == null) ? null : lookup.get(input.toLowerCase());
        }
    }

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
