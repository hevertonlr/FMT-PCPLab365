package com.lab365.app.pcp.infra.config;

import com.lab365.app.pcp.controller.dto.response.ClassroomResponseDTO;
import com.lab365.app.pcp.controller.dto.response.CourseResponseDTO;
import com.lab365.app.pcp.controller.dto.response.GradeResponseDTO;
import com.lab365.app.pcp.controller.dto.response.StudentResponseDTO;
import com.lab365.app.pcp.controller.dto.response.SubjectResponseDTO;
import com.lab365.app.pcp.controller.dto.response.TeacherResponseDTO;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseDTOConfig {

    @Bean
    ClassroomResponseDTO classroomResponse() {
        return new ClassroomResponseDTO();
    }

    @Bean
    CourseResponseDTO courseResponse() {
        return new CourseResponseDTO();
    }

    @Bean
    SubjectResponseDTO subjectResponse() {
        return new SubjectResponseDTO();
    }

    @Bean
    GradeResponseDTO gradeResponse() {
        return new GradeResponseDTO();
    }

    @Bean
    StudentResponseDTO studentResponse() {
        return new StudentResponseDTO();
    }

    @Bean
    TeacherResponseDTO teacherResponse() {
        return new TeacherResponseDTO();
    }
}
