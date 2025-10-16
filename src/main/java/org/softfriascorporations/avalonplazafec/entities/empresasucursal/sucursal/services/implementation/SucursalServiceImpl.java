package org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.services.implementation;

import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.repositories.SucursalRepository;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.services.interfaces.ServiceSucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl implements ServiceSucursal {

    @Autowired
    SucursalRepository sucursalRepository;


    @Override
    public List<SucursalDto> findAll() {


        return List.of();
    }

    @Override
    public SucursalDto findById(Long aLong) {
        return null;
    }

    @Override
    public SucursalDto save(SucursalDto sucursalDto) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
