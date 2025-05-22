package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.lab365.app.pcp.datasource.enums.CivilStateEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherRequestDTO extends PersonRequestDTO {
        private CivilStateEnum civilState;
        private String nationality;
        @NotNull(message = "User is required")
        private UserCreateRequest user;
        private List<Long> subjectIds;
}
