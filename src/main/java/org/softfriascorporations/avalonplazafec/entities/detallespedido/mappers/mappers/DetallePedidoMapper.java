package org.softfriascorporations.avalonplazafec.entities.detallespedido.mappers.mappers;

import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.entities.DetallePedido;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.entities.DetallesVenta;

import java.util.ArrayList;
import java.util.List;


public class DetallePedidoMapper {

    public  static List<DetallesPedidoDto > toListDto (List<DetallePedido> dv){

        List<DetallesPedidoDto> detallesPedidoDtoList = new ArrayList<>();


        dv.forEach( listaDetalles -> detallesPedidoDtoList.add(toDto(listaDetalles)));

        return detallesPedidoDtoList;


    }
    public  static DetallesPedidoDto toDto (DetallePedido dv){

        return DetallesPedidoDto.builder()
                .id(dv.getId())
                .cantidad(dv.getCantidad())
                .precioUnitario(dv.getPrecioUnitario())
                .subTotal(dv.getSubTotal())
                .producto(ProductoMapper.toDto(dv.getProducto()))
                .build();


    }
    public  static DetallePedido toEntity (DetallesPedidoDto dv){

        return DetallePedido.builder()
                //el id se asigna automaticamente en el guardado
                .cantidad(dv.getCantidad())
                .precioUnitario(dv.getPrecioUnitario())
                //el sub total se asigna automaticamente antes de guardar la venta
                //el id de pedido se asigna desde el service
                //producto se mapea y se asigna desde el servicio
                .build();


    }
}
