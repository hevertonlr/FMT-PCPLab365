package com.lab365.app.pcp.controller.dto.request;

import com.lab365.app.pcp.datasource.entity.Classroom;
import com.lab365.app.pcp.datasource.entity.Course;
import com.lab365.app.pcp.datasource.entity.Teacher;
import jakarta.validation.constraints.NotBlank;

public record ClassroomRequest(@NotBlank String name, Long teacherid, Long courseid) {
    public Classroom toEntity() {

        Classroom entity = new Classroom();
        entity.setName(name);
        if (teacherid != null) {
            Teacher teacher = new Teacher();
            teacher.setId(teacherid);
            entity.setTeacher(teacher);
        }
        if (courseid != null) {
            Course course = new Course();
            course.setId(courseid);
            entity.setCourse(course);
        }
        return entity;
    }
}
