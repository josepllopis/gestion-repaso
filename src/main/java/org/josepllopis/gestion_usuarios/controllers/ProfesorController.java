package org.josepllopis.gestion_usuarios.controllers;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.service.ProfesorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/profesores")
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;


    @GetMapping("/{id}")
    @Operation(summary = "Devolver profesor", description = "Devuelve el profesor según el id")
    public ResponseEntity<ResponseProfesorDTO> getProfesor(@PathVariable Long id){
        return profesorService.getProfesor(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping
    @Operation(summary = "Listar profesores", description = "Devuelve la lista de todos los profesores de la BD")
    public ResponseEntity<List<ResponseProfesorDTO>> getProfesores(){
        List<ResponseProfesorDTO> listaProfesores = profesorService.getProfesores();

        if(listaProfesores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(profesorService.getProfesores());
    }

    @Operation(summary = "Añadir profesor", description = "Añade un profesor a la BD")
    @PostMapping
    public ResponseEntity<ResponseProfesorDTO> addProfesor(@Valid @RequestBody RequestProfesorDTO profesorDTO){
        ResponseProfesorDTO create = profesorService.addProfesor(profesorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @Operation(summary = "Actualizar profesor", description = "Actualiza un profesor de la BD")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseProfesorDTO> updateProfesor(@PathVariable Long id, @RequestBody RequestProfesorDTO profesorDTO){
        return profesorService.updateProfesor(id, profesorDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Eliminar profesor", description = "Eliminar un profesor de la BD")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id){
        boolean delete = profesorService.deleteProfesor(id);

        if(!delete){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Devolver los alumnos del profesor", description = "Devuelve una lista de los alumnos vinculados a un determinado profesor")
    @GetMapping("/alumnos/{id}")
    public ResponseEntity<List<ResponseAlumnoDTO>> devolverAlumnosDeProfesor(@PathVariable  Long id){
        Optional<ResponseProfesorDTO> profesor = profesorService.getProfesor(id);

        if(profesor.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return  ResponseEntity.ok(profesorService.devolverAlumnos(id));
        }
    }

    @Operation(summary = "Devolver las asignaturas del profesor", description = "Devuelve una lista de las asignaturas vinculadas a un determinado profesor")
    @GetMapping("/asignaturas/{id}")
    public ResponseEntity<List<ResponseAsignaturaDTO>> devolverAsignaturasDeProfesor(@PathVariable  Long id){
        Optional<ResponseProfesorDTO> profesor = profesorService.getProfesor(id);

        if (profesor.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(profesorService.devolverAsignaturas(id));
    }
}
