package org.softfriascorporations.avalonplazafec.entities.usuarios.mappers;

import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;
import org.softfriascorporations.avalonplazafec.entities.usuarios.entities.Usuario;

public class UsuarioMapper {

   public static UsuarioDto toDto(Usuario usuario) {
        return UsuarioDto.builder()
                .id(usuario.getId())
                .userName(usuario.getNombreUsuario())
                //.password(usuario.getHashpassword())
                .roleId(usuario.getMaesRol())

                .build();
    }


    public static Usuario toEntity(UsuarioDto u) {

        return Usuario.builder()
                .id(u.getId())
                .nombreUsuario(u.getUserName())
                .hashpassword(u.getPassword())
                .build();
    }
}
