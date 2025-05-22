package com.lab365.app.pcp.controller.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class StudentTotalScoreResponseDTO {
        private Long courseId;
        private BigDecimal totalscore;

        public static Object fromMap(Map<Long, BigDecimal> mapScore) {
                int size = mapScore.size();
                return size == 1 ? fromEntry(mapScore.entrySet().iterator().next())
                                : mapScore.entrySet().stream().map(StudentTotalScoreResponseDTO::fromEntry)
                                                .collect(Collectors.toList());
        }

        private static StudentTotalScoreResponseDTO fromEntry(Map.Entry<Long, BigDecimal> entry) {
                StudentTotalScoreResponseDTO response = new StudentTotalScoreResponseDTO();
                response.courseId = entry.getKey();
                response.totalscore = entry.getValue();
                return response;
        }
}
