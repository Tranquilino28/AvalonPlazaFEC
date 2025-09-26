package org.softfriascorporations.avalonplazafec.entities.ventas.repositories;

import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

    Optional<Venta> findByCodigoVenta(UUID codigoVenta);
}
