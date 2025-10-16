package org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.dtos.EmpresaDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SucursalDto {
    private Long id;
    private String codigoSucursal;
    private String nombre;
    private String direccion;
    private String telefono;
    private boolean principal;
    private MaestraDto estado;
    //private EmpresaDto empresa;
}
