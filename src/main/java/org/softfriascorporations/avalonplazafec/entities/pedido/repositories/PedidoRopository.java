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

    /*@Query(
            """
                    SELECT c FROM Pedido c
                    LEFT JOIN FETCH c.detalles d
                    LEFT JOIN FETCH d.producto p
                    LEFT JOIN FETCH p.categoria 
                    LEFT JOIN FETCH p.medida
                    WHERE c.estado = :estado
                    """
    )
     */

    Optional<Pedido> findByEstado(Maestra estado);
}
