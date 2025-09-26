package org.softfriascorporations.avalonplazafec.entities.usuarios.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDto {
    private Long id;
    private String userName;
    private String password;
    private Maestra roleId;
    //private Long empresaId;
}
