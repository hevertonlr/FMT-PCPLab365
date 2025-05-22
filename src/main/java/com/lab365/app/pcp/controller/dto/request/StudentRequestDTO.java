package com.lab365.app.pcp.controller.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentRequestDTO extends PersonRequestDTO {
    private String placeofbirth;

    @NotNull
    private Long classroomid;

    @NotNull(message = "User is required")
    private UserCreateRequest user;
}
