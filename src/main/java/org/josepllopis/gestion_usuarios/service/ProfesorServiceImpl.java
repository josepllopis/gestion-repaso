package org.josepllopis.gestion_usuarios.service;

import lombok.AllArgsConstructor;
import org.josepllopis.gestion_usuarios.domain.Profesor;
import org.josepllopis.gestion_usuarios.dto.RequestProfesorDTO;
import org.josepllopis.gestion_usuarios.dto.ResponseProfesorDTO;
import org.josepllopis.gestion_usuarios.mapper.ProfesorMapper;
import org.josepllopis.gestion_usuarios.repositories.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfesorServiceImpl implements ProfesorService {

    private ProfesorRepository repoProfe;
    private ProfesorMapper mapperProfe;

    @Override
    public List<ResponseProfesorDTO> getProfesores() {
        return repoProfe.findAll().stream().map(mapperProfe::toResponse).toList();
    }

    @Override
    public Optional<ResponseProfesorDTO> getProfesor(Long id) {
        return repoProfe.findById(id).map(mapperProfe::toResponse);
    }

    @Override
    public ResponseProfesorDTO addProfesor(RequestProfesorDTO profesorDTO) {
        Profesor prof = mapperProfe.toEntity(profesorDTO,new HashSet<>(),new HashSet<>());
        Profesor profesorInsertado = repoProfe.save(prof);
        return mapperProfe.toResponse(profesorInsertado);
    }

    @Override
    public Optional<ResponseProfesorDTO> updateProfesor(Long id,RequestProfesorDTO profesorDTO) {
        return Optional.empty();
    }

    @Override
    public boolean deleteProfesor(Long id) {
        if(!repoProfe.existsById(id)){
            return false;
        }
        repoProfe.deleteById(id);
        return true;
    }
}
