package org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.repositories;

import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByNit(String nit);
    Boolean existsByNit(String nit);
}
