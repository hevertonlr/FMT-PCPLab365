package com.lab365.app.pcp.controller.dto.response;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

public record StudentTotalScoreResponse(Long courseId, BigDecimal totalscore) {

    public static Object fromMap(Map<Long, BigDecimal> mapScore) {
        int size = mapScore.size();
        return size == 1
                ? new StudentTotalScoreResponse(mapScore.keySet().iterator().next(), mapScore.values().iterator().next())
                : mapScore.entrySet().stream()
                .map(entry -> new StudentTotalScoreResponse(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
