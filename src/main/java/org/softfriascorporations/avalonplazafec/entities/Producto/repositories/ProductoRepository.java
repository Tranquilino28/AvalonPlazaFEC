package org.softfriascorporations.avalonplazafec.entities.Producto.repositories;

import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    Producto findByCodigoBarras(String codigoBarras);
}
