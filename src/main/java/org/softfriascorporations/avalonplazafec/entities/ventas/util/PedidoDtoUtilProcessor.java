package org.softfriascorporations.avalonplazafec.entities.ventas.util;

import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.mappers.mappers.DetallePedidoMapper;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.mappers.DetallesVentaMapper;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.softfriascorporations.avalonplazafec.entities.pedido.mappers.PedidoMapper;
import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;
import org.softfriascorporations.avalonplazafec.entities.ventas.mappers.VentaMapper;

import java.util.ArrayList;
import java.util.List;

public class PedidoDtoUtilProcessor {



    public static PedidoDto ProcessPedidoDto(Pedido p) {

        List<DetallesPedidoDto> detallesPedidoDtos = new ArrayList<>();

        p.getDetalles().forEach(detalle -> {
            ProductoDto productoDto = ProductoMapper.toDto(detalle.getProducto());
            DetallesPedidoDto detalleDto = DetallePedidoMapper.toDto(detalle);
            detalleDto.setProducto(productoDto);

            detallesPedidoDtos.add(detalleDto);
        });

        PedidoDto pedidoDto = PedidoMapper.toDto(p);
        pedidoDto.setDetalles(detallesPedidoDtos);

        return pedidoDto;

    }
}
