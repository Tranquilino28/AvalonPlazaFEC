package org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.mappers;

import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.dtos.EmpresaDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.mappers.SucursalMapper;
import org.softfriascorporations.avalonplazafec.entities.maestra.mappers.MaestraMapper;

import java.util.ArrayList;
import java.util.List;

public class EmpresaMapper {

   public static EmpresaDto toDto(Empresa e){

        List<SucursalDto> sucursalesDtoList = new ArrayList<>();

        e.getSucursales().forEach(
                s -> sucursalesDtoList.add(SucursalMapper.toDto(s))
        );

        return EmpresaDto.builder()
                .id(e.getId())
                .nombre(e.getNombre())
                .nit(e.getNit())
                .email(e.getEmail())
                .estado(MaestraMapper.toDto(e.getEstado()))
                .sucursales(sucursalesDtoList)
        .build();

    }

    public static Empresa toEntity(EmpresaDto eDto) {

        List<Sucursal> sucursalesList = new ArrayList<>();


        eDto.getSucursales().forEach( s -> {

                    System.out.println("sucursal " + s);
                    sucursalesList.add(SucursalMapper.toEntity(s));
                }
        );

        return Empresa.builder()
                .id(eDto.getId())
                .nombre(eDto.getNombre())
                .nit(eDto.getNit())
                .email(eDto.getEmail())
                .sucursales(sucursalesList)
                .build();

    }
}
