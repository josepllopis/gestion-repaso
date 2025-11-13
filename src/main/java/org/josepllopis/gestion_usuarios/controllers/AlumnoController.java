package org.josepllopis.gestion_usuarios.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
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

    @GetMapping
    public ResponseEntity<List<ResponseAlumnoDTO>> getProfesores(){

        List<ResponseAlumnoDTO> listaProfesores = alumnoService.getAlumnos();

        if(listaProfesores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alumnoService.getAlumnos());
    }

    @PostMapping
    public ResponseEntity<ResponseAlumnoDTO> addProfesor(@Valid @RequestBody RequestAlumnoDTO alumnoDTO){
        ResponseAlumnoDTO create = alumnoService.addAlumno(alumnoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAlumnoDTO> updateAlumno(@PathVariable Long id, @RequestBody RequestAlumnoDTO alumnoDTO){
       return alumnoService.updateAlumno(id,alumnoDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id){
        boolean delete = alumnoService.deleteAlumno(id);

        if(!delete){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idProfesor}/vincular-alumno-profesor/{idAlumno}")
    public ResponseEntity<ResponseAlumnoDTO> vincularProfesorAlumno(@PathVariable Long idProfesor, @PathVariable Long idAlumno){
        ResponseAlumnoDTO alumno = alumnoService.asignarAlumno(idProfesor,idAlumno);
        return ResponseEntity.status(HttpStatus.OK).body(alumno);
    }

    @PutMapping("/{idProfesor}/desvincular-alumno-profesor/{idAlumno}")
    public ResponseEntity<ResponseAlumnoDTO> desVincularProfesorAlumno(@PathVariable Long idProfesor, @PathVariable Long idAlumno){
        ResponseAlumnoDTO alumno = alumnoService.desAsignarAlumno(idProfesor,idAlumno);
        return ResponseEntity.status(HttpStatus.OK).body(alumno);
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<List<ResponseProfesorDTO>> devolverProfesoresDeAlumno(@PathVariable Long id){
        Optional<ResponseAlumnoDTO> alumno = alumnoService.getAlumno(id);

        if(alumno.isEmpty()){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumnoService.devolverProfesores(id));

    }

    @GetMapping("/asignaturas/{id}")
    public ResponseEntity<List<ResponseAsignaturaDTO>> devolverAsignaturasDeAlumno(@PathVariable Long id){

        Optional<ResponseAlumnoDTO> alumno = alumnoService.getAlumno(id);
        if(alumno.isEmpty()){
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(alumnoService.devolverAsignaturas(id));
    }

}
