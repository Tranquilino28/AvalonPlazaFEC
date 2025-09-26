package org.softfriascorporations.avalonplazafec.entities.usuarios.controllers;

import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;
import org.softfriascorporations.avalonplazafec.entities.usuarios.entities.Usuario;
import org.softfriascorporations.avalonplazafec.entities.usuarios.services.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/save")
    public ResponseEntity<Boolean> crearUsuario(@RequestBody UsuarioDto usuarioDto) {

           usuarioService.save(usuarioDto);
           return ResponseEntity.ok(true);
    }

    @GetMapping("/search/s1/{userName}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String userName) {

             return ResponseEntity.ok(usuarioService.findByNombreUsuario(userName).get());
    }

    @PutMapping("/delete/{userName}")
        public ResponseEntity<Boolean> eliminarUsuario(@PathVariable String userName) {

        return ResponseEntity.ok(
                usuarioService.deleteById(
                        usuarioService.findByNombreUsuario(userName).get().getId()
                )
                );

    }
    @GetMapping("/all")
    public ResponseEntity<List<UsuarioDto>> obtenerUsuarios() {

        return ResponseEntity.ok(usuarioService.findAll());


    }


}
