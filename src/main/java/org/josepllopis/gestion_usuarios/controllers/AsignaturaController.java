package org.josepllopis.gestion_usuarios.controllers;

import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.mapper.AsignaturaMapper;
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

    @PutMapping("/{id}")
    public ResponseEntity<ResponseAsignaturaDTO> updateAsignatura(@PathVariable Long id, @RequestBody RequestAsignaturaDTO asignaturaDTO){
        return asignaturaService.updateAsignatura(id,asignaturaDTO).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //VINCULAR
    @PutMapping("/{idProfesor}/vincular-profesor-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> vincularProfesorAsignatura(@PathVariable Long idProfesor, @PathVariable Long idAsignatura){
        ResponseAsignaturaDTO asignatura = asignaturaService.asignarAsignaturaProf(idProfesor,idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @PutMapping("/{idAlumno}/vincular-alumno-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> vincularAlumnoAsignatura(@PathVariable Long idAlumno, @PathVariable Long idAsignatura){
        ResponseAsignaturaDTO asignatura = asignaturaService.asignarAsignaturaAlumn(idAlumno,idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    //DESVINCULAR
    @PutMapping("/{idProfesor}/desvincular-profesor-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> desVincularProfesorAsignatura(@PathVariable Long idProfesor, @PathVariable Long idAsignatura){
        ResponseAsignaturaDTO asignatura = asignaturaService.desAsignarAsignaturaProf(idProfesor,idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @PutMapping("/{idAlumno}/desvincular-alumno-asignatura/{idAsignatura}")
    public ResponseEntity<ResponseAsignaturaDTO> desVincularAlumnoAsignatura(@PathVariable Long idAlumno, @PathVariable Long idAsignatura){
        ResponseAsignaturaDTO asignatura = asignaturaService.desAsignarAsignaturaAlumn(idAlumno,idAsignatura);
        return ResponseEntity.status(HttpStatus.OK).body(asignatura);
    }

    @GetMapping("/profesores/{id}")
    public ResponseEntity<List<ResponseProfesorDTO>> devolverProfesoresDeAsignaturas(@PathVariable Long id){
        Optional<ResponseAsignaturaDTO> asignatura = asignaturaService.getAsignatura(id);

        if(asignatura.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(asignaturaService.devolverProfesores(id));
    }

    @GetMapping("/alumnos/{id}")
    public ResponseEntity<List<ResponseAlumnoDTO>> devolverAlumnosDeAsignaturas(@PathVariable Long id){
        Optional<ResponseAsignaturaDTO> asignatura = asignaturaService.getAsignatura(id);

        if(asignatura.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(asignaturaService.devolverAlumnos(id));
    }
}
