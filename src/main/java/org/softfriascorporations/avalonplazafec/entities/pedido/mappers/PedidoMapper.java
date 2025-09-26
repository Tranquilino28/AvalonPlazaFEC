package org.softfriascorporations.avalonplazafec.entities.pedido.mappers;

import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;

public class PedidoMapper {

    public static PedidoDto toDto(Pedido pedido) {
        return PedidoDto.builder()
                //.id(venta.getId())
                .codigoPedido(pedido.getCodigoPedido())
                .valorTotal(pedido.getValorTotal())
                .fechaPedido(pedido.getFechaPedido())
                .metodo_de_pago(pedido.getMetodo_de_pago().getNombreLargo())
                //la lista de detallesventa se mapea y se asigna desde el servicio
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
