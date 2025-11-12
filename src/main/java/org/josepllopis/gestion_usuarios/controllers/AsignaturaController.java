package org.josepllopis.gestion_usuarios.controllers;

import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.mapper.AsignaturaMapper;
import org.josepllopis.gestion_usuarios.service.AsignaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/asignaturas")
@RequiredArgsConstructor
public class AsignaturaController {

    private final AsignaturaService asignaturaService;
    private final AsignaturaMapper asignaturaMapper;


    @GetMapping
    public ResponseEntity<List<ResponseAsignaturaDTO>> getAsignaturas(){
        List<ResponseAsignaturaDTO> asignaturas = asignaturaService.getAsignaturas();

        if(asignaturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(asignaturas);
    }

    @PostMapping
    public ResponseEntity<ResponseAsignaturaDTO> addAsignatura(@RequestBody RequestAsignaturaDTO asignaturaDTO){
        ResponseAsignaturaDTO asignaturaDTO1 = asignaturaService.createAsignatura(asignaturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(asignaturaDTO1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable Long id){
        boolean delete = asignaturaService.deleteAsignatura(id);

        if(!delete){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idProfesor}/vincular-profesor/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> vincularProfesorAsignatura(@PathVariable Long idProfesor, @PathVariable Long idAsignatura){
        ResponseAsignaturaDTO asignatura = asignaturaService.asignarAsignaturaProf(idProfesor,idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @PutMapping("/{idAlumno}/vincular-alumno/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> vincularAlumnoAsignatura(@PathVariable Long idAlumno, @PathVariable Long idAsignatura){
        ResponseAsignaturaDTO asignatura = asignaturaService.asignarAsignaturaAlumn(idAlumno,idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }
}
