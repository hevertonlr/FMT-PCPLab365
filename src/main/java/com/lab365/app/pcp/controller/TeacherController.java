package com.lab365.app.pcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import com.lab365.app.pcp.controller.dto.request.TeacherCreateRequest;
import com.lab365.app.pcp.controller.dto.request.TeacherUpdateRequest;
import com.lab365.app.pcp.controller.dto.response.TeacherResponse;
import com.lab365.app.pcp.datasource.entity.Teacher;
import com.lab365.app.pcp.service.AddressService;
import com.lab365.app.pcp.service.TeacherService;
import com.lab365.app.pcp.service.UserService;

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
public class TeacherController extends GenericController<Teacher, TeacherResponse> {

    private final UserService userService;
    private final TeacherResponse teacherResponse;
    private final AddressService addressService;
    private final TeacherService service;

    public TeacherController(TeacherService service, UserService userService, AddressService addressService,
            TeacherResponse teacherResponse) {
        super(service, teacherResponse);
        this.userService = userService;
        this.addressService = addressService;
        this.teacherResponse = teacherResponse;
        this.service = service;
    }

    @Operation(summary = "Criar", description = "Cria um novo docente")
    @PostMapping
    public ResponseEntity<TeacherResponse> create(@Valid @RequestBody TeacherCreateRequest request) {
        log.info("POST /docentes");
        Teacher entity = request.toEntity();
        userService.save(entity.getUser());
        addressService.save(entity.getAddress());
        Teacher savedEntity = super.service.save(entity);
        log.info("POST /docentes -> Cadastrado");
        TeacherResponse response = teacherResponse.fromEntity(savedEntity);
        log.debug("POST /docentes -> Response Body:\n{}\n", toJSON(response));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Atualizar", description = "Atualiza um docente")
    @PutMapping("{id}")
    public ResponseEntity<TeacherResponse> update(@RequestBody TeacherUpdateRequest request, @PathVariable Long id) {
        log.info("PUT /docentes/{} -> Início", id);
        Teacher entity = request.toEntity();
        userService.save(entity.getUser());
        addressService.save(entity.getAddress());
        entity.setId(id);
        Teacher savedEntity = super.service.save(entity);
        log.info("PUT /docentes/{} -> Atualizado", id);
        TeacherResponse response = teacherResponse.fromEntity(savedEntity);
        log.debug("PUT /docentes/{} -> Response Body:\n{}\n", id, toJSON(response));
        return ResponseEntity.ok(response);
    }

    /*
     * @Operation(summary = "Listar", description = "Lista os docentes cadastrados")
     * 
     * @GetMapping()
     * public ResponseEntity<List<TeacherResponse>> list(@RequestParam(required =
     * false) @Relations({"address", "user"}) Set<String> relations) {
     * log.info("GET /docentes -> Início");
     * List<Teacher> findedEntities = service.findAllWithRelations(relations);
     * log.info("GET /docentes -> Encontrado(s) {} Docente(s)",
     * findedEntities.size());
     * List<TeacherResponse> entities =
     * findedEntities.stream().map(teacherResponse::fromEntity).toList();
     * log.debug("GET /docentes -> Response Body:\n{}\n", toJSON(entities));
     * return ResponseEntity.ok(entities);
     * }
     */

    // @Override
    @Operation(summary = "Buscar Detalhes", description = "Busca uma entidade específica pelo ID com todos os detalhes")
    @GetMapping("{id}/details")
    public ResponseEntity<TeacherResponse> findDetailsById(@PathVariable Long id) {
        String methodPath = getPathMethod();
        log.info("GET {} -> Início", methodPath);
        Teacher entity = service.findByIdWithAllDetails(id);
        log.info("GET {} -> Encontrado(a)", methodPath);
        TeacherResponse response = teacherResponse.fromEntity(entity);
        log.debug("GET {} -> Response Body:\n{}\n", methodPath, response);
        return ResponseEntity.ok(response);
    }
}
