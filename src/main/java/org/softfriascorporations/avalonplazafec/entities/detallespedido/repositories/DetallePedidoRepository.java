package org.softfriascorporations.avalonplazafec.entities.detallespedido.repositories;

import org.softfriascorporations.avalonplazafec.entities.detallespedido.entities.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
