package org.softfriascorporations.avalonplazafec.entities.usuarios.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.crudService.GenericService;
import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;
import org.softfriascorporations.avalonplazafec.entities.usuarios.entities.Usuario;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UsuarioService extends GenericService<UsuarioDto,Long> {

    Page<Usuario> findAll(int page, int size);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);


}
