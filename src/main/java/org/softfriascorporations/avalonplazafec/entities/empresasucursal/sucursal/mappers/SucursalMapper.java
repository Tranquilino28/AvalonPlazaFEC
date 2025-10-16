package org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.mappers;

import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.dtos.EmpresaDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.mappers.EmpresaMapper;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.softfriascorporations.avalonplazafec.entities.maestra.mappers.MaestraMapper;

public class SucursalMapper {

    public static SucursalDto toDto(Sucursal s) {

      //  EmpresaDto empresaDto = EmpresaMapper.toDto(s.getEmpresa());

        return SucursalDto.builder()
                .id(s.getId())
                .codigoSucursal(s.getCodigoSucursal())
                .direccion(s.getDireccion())
                .telefono(s.getTelefono())
                .principal(s.isPrincipal())
                .nombre(s.getNombre())
                .estado(MaestraMapper.toDto(s.getEstado()))
                //.empresa(empresaDto)


                .build();
    }

    public static Sucursal toEntity(SucursalDto s) {


        return Sucursal.builder()
                .id(s.getId())
                .direccion(s.getDireccion())
                .telefono(s.getTelefono())
                .principal(s.isPrincipal())
                .nombre(s.getNombre())
                //el estado se añade desde el service
                //.empresa(EmpresaMapper.toEntity(s.getEmpresa()))
                .build();
    }


}
