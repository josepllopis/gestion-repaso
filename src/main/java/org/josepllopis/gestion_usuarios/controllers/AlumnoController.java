package org.josepllopis.gestion_usuarios.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AlumnoController {

    @GetMapping("/hola")
    public String prueba(){
        return "Ya estamos aquí";
    }
}
