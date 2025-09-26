package org.softfriascorporations.avalonplazafec.entities.pedidosventas.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;

public interface PedidoVentaService {

    VentaDto facturarPedidoVenta(String codigoPedido, String metodoPago);

}
