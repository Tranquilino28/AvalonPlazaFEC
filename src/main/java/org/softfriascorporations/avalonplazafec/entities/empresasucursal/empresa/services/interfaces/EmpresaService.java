package org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.crudService.GenericService;
import org.softfriascorporations.avalonplazafec.entities.crudService.GenericServiceImpl;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.dtos.EmpresaDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaService extends GenericService<EmpresaDto, Long> {

    EmpresaDto addSucursal(Long empresaId, SucursalDto sDto);
}
