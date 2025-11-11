package org.josepllopis.gestion_usuarios.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
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


    @GetMapping
    public ResponseEntity<List<ResponseProfesorDTO>> getProfesores(){

        List<ResponseProfesorDTO> listaProfesores = profesorService.getProfesores();

        if(listaProfesores.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(profesorService.getProfesores());
    }

    @PostMapping
    public ResponseEntity<ResponseProfesorDTO> addProfesor(@Valid @RequestBody RequestProfesorDTO profesorDTO){
        ResponseProfesorDTO create = profesorService.addProfesor(profesorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(create);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable Long id){
        boolean delete = profesorService.deleteProfesor(id);

        if(!delete){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
