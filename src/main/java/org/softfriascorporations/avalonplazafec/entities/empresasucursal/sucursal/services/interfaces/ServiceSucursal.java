package org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.crudService.GenericService;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceSucursal extends GenericService<SucursalDto, Long> {
}
