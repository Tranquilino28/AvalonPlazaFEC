package org.softfriascorporations.avalonplazafec.entities.pedido.repositories;

import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoRopository extends JpaRepository<Pedido, Long> {

    Pedido findByCodigoPedido(UUID codigo);
}
