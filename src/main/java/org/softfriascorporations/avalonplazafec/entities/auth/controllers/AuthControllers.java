package org.softfriascorporations.avalonplazafec.entities.auth.controllers;

import org.softfriascorporations.avalonplazafec.entities.auth.services.interfaces.AuthService;
import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;
import org.softfriascorporations.avalonplazafec.entities.usuarios.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthControllers {

    @Autowired
    AuthService authService;


    @PostMapping("/login")
    public Boolean login(@RequestBody UsuarioDto usuario) {
        return authService.authenticate(usuario);


    }


}
