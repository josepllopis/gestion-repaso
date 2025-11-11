package org.josepllopis.gestion_usuarios.service;

import lombok.RequiredArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Asignatura;
import org.josepllopis.gestion_usuarios.dto.RequestAsignaturaDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAlumnoDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseAsignaturaDTO;
import org.josepllopis.gestion_usuarios.mapper.AsignaturaMapper;
import org.josepllopis.gestion_usuarios.mapper.ProfesorMapper;
import org.josepllopis.gestion_usuarios.repositories.AsignaturaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AsignaturaServiceImpl implements AsignaturaService{

    private final AsignaturaRepository asignaturaRepository;
    private final AsignaturaMapper asignaturaMapper;

    @Override
    public List<ResponseAsignaturaDTO> getAsignaturas() {
        return asignaturaRepository.findAll().stream().map(asignaturaMapper::toResponse).toList();
    }

    @Override
    public Optional<ResponseAsignaturaDTO> getAsignatura(Long id) {
        return asignaturaRepository.findById(id).map(asignaturaMapper::toResponse);
    }

    @Override
    public ResponseAsignaturaDTO createAsignatura(RequestAsignaturaDTO asignaturaDTO) {
        Asignatura asignatura = asignaturaMapper.toEntity(asignaturaDTO,new HashSet<>(),new HashSet<>());
        Asignatura asignaturaCreada = asignaturaRepository.save(asignatura);
        return asignaturaMapper.toResponse(asignaturaCreada);
    }

    @Override
    public Optional<ResponseAsignaturaDTO> updateAsignatura(Long id, ResponseAsignaturaDTO asignaturaDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteAsignatura(Long id) {
        if(!asignaturaRepository.existsById(id)){
            return false;
        }

        asignaturaRepository.deleteById(id);
        return true;
    }
}
