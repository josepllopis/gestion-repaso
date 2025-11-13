package org.josepllopis.gestion_usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestAlumnoDTO {

    private String nombre;
    private String apellidos;
    private String localidad;
    private String tlfn;
    private String email;
}
