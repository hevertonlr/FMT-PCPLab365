package com.lab365.app.pcp.controller.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.lab365.app.pcp.datasource.enums.CivilStateEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherResponseDTO extends PersonResponseDTO {
    private CivilStateEnum civilState;
    private String nationality;
    private UserDTO user;
}