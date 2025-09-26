package org.softfriascorporations.avalonplazafec.entities.auth.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;

public interface AuthService {


    boolean authenticate(UsuarioDto u);

}
