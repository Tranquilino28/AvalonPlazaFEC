package org.softfriascorporations.avalonplazafec.entities.pedido.mappers;

import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.mappers.mappers.DetallePedidoMapper;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.mappers.DetallesVentaMapper;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.mappers.MaestraMapper;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;

public class PedidoMapper {

    public static PedidoDto toDto(Pedido pedido) {



        return PedidoDto.builder()
                .id(pedido.getId())
                .codigoPedido(pedido.getCodigoPedido())
                .valorTotal(pedido.getValorTotal())
                .fechaPedido(pedido.getFechaPedido())
                .metodoPago(MaestraMapper.toDto(pedido.getMetodoPago()))
                .detalles(DetallePedidoMapper.toListDto(pedido.getDetalles()))
                .estadoPedido(MaestraMapper.toDto(pedido.getEstado()))
                .build();

    }
    public static Pedido toEntity(PedidoDto pedidoDto) {
        return Pedido.builder()
                //el id se asigna automaticamente en ele guardado
                //el codigo de la venta se asigna automaticamente
                .valorTotal(pedidoDto.getValorTotal())
                .fechaPedido(pedidoDto.getFechaPedido())

                //el metodo de pago se asigna desde el service
                //la lista de detallesventa se mapea y se asigna desde el servicio
                .build();

    }
}
