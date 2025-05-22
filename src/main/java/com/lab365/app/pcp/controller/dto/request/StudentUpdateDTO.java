package com.lab365.app.pcp.controller.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentUpdateDTO extends PersonUpdateDTO {
    private String placeofbirth;
    private Long userId;
    private Long classroomId;
}
