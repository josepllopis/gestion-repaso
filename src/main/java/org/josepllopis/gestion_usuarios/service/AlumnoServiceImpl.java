package org.josepllopis.gestion_usuarios.service;

import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Alumno;
import org.josepllopis.gestion_usuarios.dto.RequestAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.mapper.AlumnoMapper;
import org.josepllopis.gestion_usuarios.repositories.AlumnoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService{

    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    @Override
    public List<ResponseAlumnoDTO> getAlumnos() {
        return alumnoRepository.findAll().stream().map(alumnoMapper::toResponse).toList();
    }

    @Override
    public Optional<ResponseAlumnoDTO> getAlumno(Long id) {
        return alumnoRepository.findById(id).map(alumnoMapper::toResponse);
    }

    @Override
    public ResponseAlumnoDTO addAlumno(RequestAlumnoDTO alumnoDTO) {
        Alumno alumno = alumnoMapper.toEntity(alumnoDTO,new HashSet<>(),new HashSet<>());
        Alumno alumnoCreado = alumnoRepository.save(alumno);
        return alumnoMapper.toResponse(alumnoCreado);
    }

    @Override
    public Optional<ResponseAlumnoDTO> updateAlumno(Long id, RequestAlumnoDTO alumnoDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteAlumno(Long id) {
        if(!alumnoRepository.existsById(id)){
            return false;
        }

        alumnoRepository.deleteById(id);
        return true;
    }
}
