package com.lab365.app.pcp.controller.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StudentResponseDTO extends PersonResponseDTO {
    private String placeofbirth;
    private UserDTO user;
    private ClassroomDTO classroom;
}