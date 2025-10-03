package org.softfriascorporations.avalonplazafec.entities.carrito.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;

import java.util.Optional;

public interface CarritoService {

    PedidoDto addCarrito(DetallesPedidoDto detalle);
    Optional<Pedido> searchCarrito();
    PedidoDto deleteItem(Long carritoId, Long itemId);

    PedidoDto getCarrito();

    PedidoDto carritoToPedido(Long carritoId, Long metodoPagoId);
}
