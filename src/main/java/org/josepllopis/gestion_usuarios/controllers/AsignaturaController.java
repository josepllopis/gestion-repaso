package org.josepllopis.gestion_usuarios.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.service.AsignaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {

    private final AsignaturaService asignaturaService;


    @GetMapping("/{id}")
    @Operation(summary = "Devolver asignatura", description = "Devuelve la asignatura según el id")
    public ResponseEntity<ResponseAsignaturaDTO> getAsignatura(@PathVariable Long id) {
        return asignaturaService.getAsignatura(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar asignaturas", description = "Devuelve la lista de todas las asignaturas de la BD")
    @GetMapping
    public ResponseEntity<List<ResponseAsignaturaDTO>> getAsignaturas() {
        List<ResponseAsignaturaDTO> asignaturas = asignaturaService.getAsignaturas();

        if (asignaturas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(asignaturas);
    }

    @Operation(summary = "Añadir asignatura", description = "Añade una asignatura a la BD")
    @PostMapping
    public ResponseEntity<ResponseAsignaturaDTO> addAsignatura(@RequestBody RequestAsignaturaDTO asignaturaDTO) {

        ResponseAsignaturaDTO asignaturaDTO1 = asignaturaService.createAsignatura(asignaturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaDTO1);
    }

    @Operation(summary = "Actualizar asignatura", description = "Actualiza una asignatura de la BD")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseAsignaturaDTO> updateAsignatura(@PathVariable Long id, @RequestBody RequestAsignaturaDTO asignaturaDTO) {
        return asignaturaService.updateAsignatura(id, asignaturaDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar asignatura", description = "Eliminar una asignatura de la BD")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id) {
        boolean delete = asignaturaService.deleteAsignatura(id);

        if (!delete) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Vincular un profesor con una asignatura", description = "Vincula un profesor a una asignatura, el profesor impartirá esta asignatura")
    @PutMapping("/{idProfesor}/vincular-profesor-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> vincularProfesorAsignatura(@PathVariable Long idProfesor, @PathVariable Long idAsignatura) {
        ResponseAsignaturaDTO asignatura = asignaturaService.asignarAsignaturaProf(idProfesor, idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @Operation(summary = "Vincular un alumno con una asignatura", description = "Vincula un alumno a una asignatura, el alumno se matricula en esta asignatura")
    @PutMapping("/{idAlumno}/vincular-alumno-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> vincularAlumnoAsignatura(@PathVariable Long idAlumno, @PathVariable Long idAsignatura) {
        ResponseAsignaturaDTO asignatura = asignaturaService.asignarAsignaturaAlumn(idAlumno, idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @Operation(summary = "Desvincular un profesor con una asignatura", description = "Desvincula un profesor a una asignatura, el profesor ya NO impartirá esta asignatura")
    @PutMapping("/{idProfesor}/desvincular-profesor-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> desVincularProfesorAsignatura(@PathVariable Long idProfesor, @PathVariable Long idAsignatura) {
        ResponseAsignaturaDTO asignatura = asignaturaService.desAsignarAsignaturaProf(idProfesor, idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @Operation(summary = "Desvincular un alumno con una asignatura", description = "Desvincula un alumno a una asignatura, el alumno ya NO está matriculado en esta asignatura")
    @PutMapping("/{idAlumno}/desvincular-alumno-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> desVincularAlumnoAsignatura(@PathVariable Long idAlumno, @PathVariable Long idAsignatura) {
        ResponseAsignaturaDTO asignatura = asignaturaService.desAsignarAsignaturaAlumn(idAlumno, idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @Operation(summary = "Devolver los profesores de una asignatura", description = "Devuelve una lista de los profesores vinculados a una determinada asignatura")
    @GetMapping("/profesores/{id}")
    public ResponseEntity<List<ResponseProfesorDTO>> devolverProfesoresDeAsignaturas(@PathVariable Long id) {
        Optional<ResponseAsignaturaDTO> asignatura = asignaturaService.getAsignatura(id);

        if (asignatura.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(asignaturaService.devolverProfesores(id));
    }

    @Operation(summary = "Devolver los alumnos de una asignatura", description = "Devuelve una lista de los alumnos vinculados a una determinada asignatura")
    @GetMapping("/alumnos/{id}")
    public ResponseEntity<List<ResponseAlumnoDTO>> devolverAlumnosDeAsignaturas(@PathVariable Long id) {
        Optional<ResponseAsignaturaDTO> asignatura = asignaturaService.getAsignatura(id);

        if (asignatura.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(asignaturaService.devolverAlumnos(id));
    }
}
