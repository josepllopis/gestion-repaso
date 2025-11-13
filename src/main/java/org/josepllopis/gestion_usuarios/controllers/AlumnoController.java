package org.josepllopis.gestion_usuarios.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.dto.*;
import org.josepllopis.gestion_usuarios.service.AlumnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/{id}")
    @Operation(summary = "Devolver alumno", description = "Devuelve el alumno según el id")
    public ResponseEntity<ResponseAlumnoDTO> getAlumno(@PathVariable Long id) {
        return alumnoService.getAlumno(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar alumnos", description = "Devuelve la lista de todos los alumnos de la BD")
    @GetMapping
    public ResponseEntity<List<ResponseAlumnoDTO>> getAlumnos() {
        List<ResponseAlumnoDTO> listaProfesores = alumnoService.getAlumnos();

        if (listaProfesores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alumnoService.getAlumnos());
    }

    @Operation(summary = "Añadir alumno", description = "Añade un alumno a la BD")
    @PostMapping
    public ResponseEntity<ResponseAlumnoDTO> addAlumno(@Valid @RequestBody RequestAlumnoDTO alumnoDTO) {
        ResponseAlumnoDTO create = alumnoService.addAlumno(alumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @Operation(summary = "Actualizar alumno", description = "Actualiza un alumno de la BD")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlumnoDTO> updateAlumno(@PathVariable Long id, @RequestBody RequestAlumnoDTO alumnoDTO) {
        return alumnoService.updateAlumno(id, alumnoDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar alumno", description = "Eliminar un alumno de la BD")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id) {
        boolean delete = alumnoService.deleteAlumno(id);

        if (!delete) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Vincular un alumno con un profesor", description = "Se vinculará el alumno al profesor, el profesor dará clase al alumno")
    @PutMapping("/{idProfesor}/vincular-alumno-profesor/{idAlumno}")
    public ResponseEntity<ResponseAlumnoDTO> vincularProfesorAlumno(@PathVariable Long idProfesor, @PathVariable Long idAlumno) {
        ResponseAlumnoDTO alumno = alumnoService.asignarAlumno(idProfesor, idAlumno);
        return ResponseEntity.status(HttpStatus.OK).body(alumno);
    }

    @Operation(summary = "Desvincular un alumno con un profesor", description = "Se desvinculará el alumno al profesor, el profesor ya NO dará clase al alumno")
    @PutMapping("/{idProfesor}/desvincular-alumno-profesor/{idAlumno}")
    public ResponseEntity<ResponseAlumnoDTO> desVincularProfesorAlumno(@PathVariable Long idProfesor, @PathVariable Long idAlumno) {
        ResponseAlumnoDTO alumno = alumnoService.desAsignarAlumno(idProfesor, idAlumno);
        return ResponseEntity.status(HttpStatus.OK).body(alumno);
    }

    @Operation(summary = "Devolver los profesores de un alumno", description = "Devuelve una lista de los profesores vinculados a un determinado alumno")
    @GetMapping("/profesores/{id}")
    public ResponseEntity<List<ResponseProfesorDTO>> devolverProfesoresDeAlumno(@PathVariable Long id) {
        Optional<ResponseAlumnoDTO> alumno = alumnoService.getAlumno(id);
        if (alumno.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumnoService.devolverProfesores(id));
    }

    @Operation(summary = "Devolver las asignaturas del alumno", description = "Devuelve una lista de las asignaturas vinculadas a un determinado alumno")
    @GetMapping("/asignaturas/{id}")
    public ResponseEntity<List<ResponseAsignaturaDTO>> devolverAsignaturasDeAlumno(@PathVariable Long id) {
        Optional<ResponseAlumnoDTO> alumno = alumnoService.getAlumno(id);
        if (alumno.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumnoService.devolverAsignaturas(id));
    }

}
