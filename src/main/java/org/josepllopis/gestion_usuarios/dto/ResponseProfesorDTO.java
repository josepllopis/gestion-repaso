package org.josepllopis.gestion_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseProfesorDTO {

    private Long id;
    private String nombre;
    private String email;
    private String tlfn;
    private String localidad;
}
