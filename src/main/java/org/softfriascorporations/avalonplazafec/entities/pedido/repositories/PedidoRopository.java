package org.softfriascorporations.avalonplazafec.entities.pedido.repositories;

import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoRopository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByCodigoPedido(UUID codigo);
    Optional<Pedido> findByEstado(Maestra estado);
}
