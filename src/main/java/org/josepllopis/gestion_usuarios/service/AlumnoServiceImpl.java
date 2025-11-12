package org.josepllopis.gestion_usuarios.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.mapper.AlumnoMapper;
import org.josepllopis.gestion_usuarios.repositories.AlumnoRepository;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService{

    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;
    private final ProfesorRepository profesorRepository;

    @Override
    @Transactional
    public List<ResponseAlumnoDTO> getAlumnos() {
        return alumnoRepository.findAll().stream().map(alumnoMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public Optional<ResponseAlumnoDTO> getAlumno(Long id) {
        return alumnoRepository.findById(id).map(alumnoMapper::toResponse);
    }

    @Override
    @Transactional
    public ResponseAlumnoDTO addAlumno(RequestAlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoMapper.toEntity(alumnoDTO);
        Alumno alumnoCreado = alumnoRepository.save(alumno);
        return alumnoMapper.toResponse(alumnoCreado);
    }

    @Override
    @Transactional
    public Optional<ResponseAlumnoDTO> updateAlumno(Long id, RequestAlumnoDTO alumnoDTO) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteAlumno(Long id) {
        if(!alumnoRepository.existsById(id)){
            return false;
        }

        alumnoRepository.deleteById(id);
        return true;
    }

    @Override
    public ResponseAlumnoDTO asignarAlumno(Long idProfesor, Long idAlumno) {

        Profesor profesor = profesorRepository.findById(idProfesor).orElseThrow(()->
                new RuntimeException("Profesor no encontrado"));
        Alumno alumno = alumnoRepository.findById(idAlumno).orElseThrow(()->
                new RuntimeException("Alumno encontrado"));



        if(profesor.getAlumnos().contains(alumno)){
            throw new RuntimeException("El alumno ya está vinculado al profesor");
        }

        profesor.getAlumnos().add(alumno);

        profesorRepository.save(profesor);

        return alumnoMapper.toResponse(alumno);

    }
}
