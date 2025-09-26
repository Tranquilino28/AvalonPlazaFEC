package org.softfriascorporations.avalonplazafec.entities.auth.services.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.auth.services.interfaces.AuthService;
import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;
import org.softfriascorporations.avalonplazafec.entities.usuarios.entities.Usuario;
import org.softfriascorporations.avalonplazafec.entities.usuarios.services.interfaces.UsuarioService;
import org.softfriascorporations.avalonplazafec.util.PassSecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UsuarioService usuarioService;

    @Transactional
    @Override
    public boolean authenticate(UsuarioDto u) {

        Optional<Usuario> uEntity = usuarioService.findByNombreUsuario(u.getUserName());

        if (!uEntity.isPresent()) {

            throw  new EntityNotFoundException("Usuario no encontrado");
        }
        try {
            return PassSecure.verifyPassword(
                    u.getPassword()
                    , uEntity.get().getHashsalt()
                    , uEntity.get().getHashpassword());

        }catch (InvalidKeySpecException e) {
           return false; //el server devuelve 200 ok pero no se inicioo login la contraseña no coinciden
        }

    }
}
