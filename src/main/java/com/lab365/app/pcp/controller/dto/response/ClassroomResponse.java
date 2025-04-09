package com.lab365.app.pcp.controller.dto.response;

import com.lab365.app.pcp.datasource.entity.Classroom;

public record ClassroomResponse(Long id, String name,
                                Long courseId) implements IGenericResponseDTO<Classroom, ClassroomResponse> {
    @Override
    public ClassroomResponse fromEntity(Classroom entity) {
        return new ClassroomResponse(entity.getId(), entity.getName(), entity.getCourse().getId());
    }
}
