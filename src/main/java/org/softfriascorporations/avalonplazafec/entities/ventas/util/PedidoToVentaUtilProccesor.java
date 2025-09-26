package org.softfriascorporations.avalonplazafec.entities.ventas.util;

import org.softfriascorporations.avalonplazafec.entities.detallespedido.entities.DetallePedido;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.entities.DetallesVenta;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PedidoToVentaUtilProccesor {

    public static Venta pedidoToVenta(Pedido pedido) {

        List<DetallesVenta> detalleventa = new ArrayList<>();

        pedido.getDetalles().forEach(detalles -> {

            DetallesVenta detallesVenta = new DetallesVenta();
            detallesVenta.setCantidad(detalles.getCantidad());
            detallesVenta.setPrecioUnitario(detalles.getPrecioUnitario());
            detallesVenta.setProducto(detalles.getProducto());
            detalleventa.add(detallesVenta);

        });

        return Venta.builder()

                .pedido(pedido)
                .detalles(detalleventa)
                .build();


    }


}
