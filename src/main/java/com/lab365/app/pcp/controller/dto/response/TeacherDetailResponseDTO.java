package com.lab365.app.pcp.controller.dto.response;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.lab365.app.pcp.datasource.enums.CivilStateEnum;
import com.lab365.app.pcp.datasource.enums.GenderEnum;

@Data
public class TeacherDetailResponseDTO {
    private Long id;
    private String name;
    private GenderEnum gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate birthday;

    private String cpf;
    private String rg;
    private String phone;
    private CivilStateEnum civilState;
    private String nationality;
    private AddressResponseDTO address;
    private UserDTO user;
    private List<SubjectDTO> subjects;
    private List<ClassroomDTO> classrooms;
}
