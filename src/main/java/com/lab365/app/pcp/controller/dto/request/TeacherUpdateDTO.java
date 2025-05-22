package com.lab365.app.pcp.controller.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.lab365.app.pcp.datasource.enums.CivilStateEnum;

@Data
@EqualsAndHashCode(callSuper = true)
public class TeacherUpdateDTO extends PersonUpdateDTO {

    private CivilStateEnum civilState;
    private String nationality;

    private UserUpdateRequest user;
    private List<Long> subjectIds;
    private List<Long> classroomIds;

}