package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import com.lab365.app.pcp.controller.dto.request.TeacherRequestDTO;
import com.lab365.app.pcp.controller.dto.request.TeacherUpdateDTO;
import com.lab365.app.pcp.controller.dto.response.TeacherResponseDTO;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.infra.mapper.TeacherMapper;
import com.lab365.app.pcp.service.interfaces.IAddressService;
import com.lab365.app.pcp.service.interfaces.ITeacherService;
import com.lab365.app.pcp.service.interfaces.IUserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.lab365.app.pcp.infra.utils.Util.getPathMethod;
import static com.lab365.app.pcp.infra.utils.Util.toJSON;

@Tag(name = "Docentes")
@Slf4j
@RestController
@RequestMapping(value = "docentes", produces = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController extends GenericController<Teacher, TeacherResponseDTO> {

    private final IUserService userService;
    private final IAddressService addressService;
    private final ITeacherService service;

    public TeacherController(ITeacherService service, IUserService userService, IAddressService addressService,
            TeacherResponseDTO teacherResponse, TeacherMapper modelMapper) {
        super(service, teacherResponse, modelMapper);
        this.userService = userService;
        this.addressService = addressService;
        this.service = service;
    }

    @Operation(summary = "Criar", description = "Cria um novo docente")
    @PostMapping
    public ResponseEntity<TeacherResponseDTO> create(@Valid @RequestBody TeacherRequestDTO request) {
        log.info("POST /docentes");
        Teacher entity = this.mapTo(request, Teacher.class);
        userService.save(entity.getUser());
        addressService.save(entity.getAddress());
        Teacher savedEntity = super.service.save(entity);
        log.info("POST /docentes -> Cadastrado");
        TeacherResponseDTO response = this.mapTo(savedEntity, TeacherResponseDTO.class);
        log.debug("POST /docentes -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza um docente")
    @PutMapping("{id}")
    public ResponseEntity<TeacherResponseDTO> update(@RequestBody TeacherUpdateDTO request, @PathVariable Long id) {
        log.info("PUT /docentes/{} -> Início", id);
        Teacher entity = this.mapTo(request, Teacher.class);
        userService.save(entity.getUser());
        addressService.save(entity.getAddress());
        entity.setId(id);
        Teacher savedEntity = super.service.save(entity);
        log.info("PUT /docentes/{} -> Atualizado", id);
        TeacherResponseDTO response = this.mapTo(savedEntity, TeacherResponseDTO.class);
        log.debug("PUT /docentes/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Listar", description = "Lista os docentes cadastrados")
    @GetMapping()
    public ResponseEntity<List<TeacherResponseDTO>> list() {
        log.info("GET /docentes -> Início");
        List<Teacher> findedEntities = service.findAll();
        log.info("GET /docentes -> Encontrado(s) {} Docente(s)", findedEntities.size());
        List<TeacherResponseDTO> entities = findedEntities.stream()
                .map(entity -> (TeacherResponseDTO) this.mapTo(entity, TeacherResponseDTO.class)).toList();
        log.debug("GET /docentes -> Response Body:\n{}\n", toJSON(entities));
        return ResponseEntity.ok(entities);
    }

    @Operation(summary = "Buscar Detalhes", description = "Busca uma entidade específica pelo ID com todos os detalhes")
    @GetMapping("{id}/details")
    public ResponseEntity<TeacherResponseDTO> findDetailsById(@PathVariable Long id) {
        String methodPath = getPathMethod();
        log.info("GET {} -> Início", methodPath);
        Teacher entity = service.findByIdWithAllDetails(id);
        log.info("GET {} -> Encontrado(a)", methodPath);
        TeacherResponseDTO response = this.mapTo(entity, TeacherResponseDTO.class);
        log.debug("GET {} -> Response Body:\n{}\n", methodPath, response);
        return ResponseEntity.ok(response);
    }

}
