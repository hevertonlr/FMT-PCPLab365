package com.lab365.app.pcp.controller.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ClassroomDetailResponseDTO {
        private Long id;
        private String name;
        private CourseDTO course;
        private List<TeacherDTO> teachers;
        private List<SubjectDTO> subjects;
        private List<StudentDTO> students;

}
