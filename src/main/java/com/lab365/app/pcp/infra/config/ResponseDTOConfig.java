package com.lab365.app.pcp.infra.config;

import com.lab365.app.pcp.controller.dto.response.ClassroomResponse;
import com.lab365.app.pcp.controller.dto.response.CourseResponse;
import com.lab365.app.pcp.controller.dto.response.GradeResponse;
import com.lab365.app.pcp.controller.dto.response.StudentResponse;
import com.lab365.app.pcp.controller.dto.response.SubjectResponse;
import com.lab365.app.pcp.controller.dto.response.TeacherResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResponseDTOConfig {

    @Bean
    ClassroomResponse classroomResponse() {
        return new ClassroomResponse(null, null, null);
    }

    @Bean
    CourseResponse courseResponse() {
        return new CourseResponse(null, null);
    }

    @Bean
    SubjectResponse subjectResponse() {
        return new SubjectResponse(null, null, null);
    }

    @Bean
    GradeResponse gradeResponse() {
        return new GradeResponse(null, null, null, null, null, null);
    }

    @Bean
    StudentResponse studentResponse() {
        return new StudentResponse(null, null, null, null, null, null, null, null, null, null);
    }

    @Bean
    TeacherResponse teacherResponse() {
        return new TeacherResponse(null, null, null, null, null, null, null, null, null, null, null);
    }
}
