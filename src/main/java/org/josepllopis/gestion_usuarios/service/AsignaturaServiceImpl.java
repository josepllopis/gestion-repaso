package org.josepllopis.gestion_usuarios.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.mapper.AsignaturaMapper;
import org.josepllopis.gestion_usuarios.mapper.ProfesorMapper;
import org.josepllopis.gestion_usuarios.repositories.AlumnoRepository;
import org.josepllopis.gestion_usuarios.repositories.AsignaturaRepository;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AsignaturaServiceImpl implements AsignaturaService{

    private final AsignaturaRepository asignaturaRepository;
    private final AsignaturaMapper asignaturaMapper;
    private final ProfesorRepository profesorRepository;
    private final AlumnoRepository alumnoRepository;

    @Override
    @Transactional
    public List<ResponseAsignaturaDTO> getAsignaturas() {
        return asignaturaRepository.findAll().stream().map(asignaturaMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public Optional<ResponseAsignaturaDTO> getAsignatura(Long id) {
        return asignaturaRepository.findById(id).map(asignaturaMapper::toResponse);
    }

    @Override
    @Transactional
    public ResponseAsignaturaDTO createAsignatura(RequestAsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = asignaturaMapper.toEntity(asignaturaDTO);
        Asignatura asignaturaCreada = asignaturaRepository.save(asignatura);
        return asignaturaMapper.toResponse(asignaturaCreada);
    }

    @Override
    @Transactional
    public Optional<ResponseAsignaturaDTO> updateAsignatura(Long id, ResponseAsignaturaDTO asignaturaDTO) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public boolean deleteAsignatura(Long id) {
        if(!asignaturaRepository.existsById(id)){
            return false;
        }

        asignaturaRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public ResponseAsignaturaDTO asignarAsignaturaProf(Long idProfesor, Long idAsignatura) {

        Profesor profesor = profesorRepository.findById(idProfesor)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        Asignatura asignatura = asignaturaRepository.findById(idAsignatura)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));



        // Solo agregamos si no está ya
        if (!profesor.getAsignaturas().contains(asignatura)) {
            profesor.getAsignaturas().add(asignatura);
        }else{
            throw new RuntimeException("La vinculación ya está hecha");
        }

        // Guardamos al profesor, Hibernate persistirá la relación ManyToMany
        profesorRepository.save(profesor);

        return asignaturaMapper.toResponse(asignatura);

    }

    @Override
    public ResponseAsignaturaDTO asignarAsignaturaAlumn(Long idAlumno, Long idAsignatura) {

        Alumno alumno = alumnoRepository.findById(idAlumno).orElseThrow(()->
                new RuntimeException("Alumno no encontrado"));
        Asignatura asignatura = asignaturaRepository.findById(idAsignatura).orElseThrow(()->
                new RuntimeException("Asignatura no encontrada"));

        if(alumno.getAsignaturas().contains(asignatura)){
            throw new RuntimeException("El alumno ya está vinculado a esta asignatura");
        }

        alumno.getAsignaturas().add(asignatura);

        alumnoRepository.save(alumno);

        return asignaturaMapper.toResponse(asignatura);
    }


}
