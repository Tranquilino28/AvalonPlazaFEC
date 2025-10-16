package org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.repositories;


import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    List<Sucursal> findByEmpresaId(Long empresaId);
}
