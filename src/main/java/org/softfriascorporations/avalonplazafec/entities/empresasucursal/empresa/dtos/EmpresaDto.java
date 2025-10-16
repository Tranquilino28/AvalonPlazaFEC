package org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;

import java.util.List;




@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmpresaDto {

    private Long id;

    private String nombre;
    private String nit;
    private String email;
    private MaestraDto estado;
    private List<SucursalDto> sucursales;

}
