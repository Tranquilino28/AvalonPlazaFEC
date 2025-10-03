package org.softfriascorporations.avalonplazafec.entities.detalleventa.mappers;

import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.entities.DetallesVenta;

import java.util.ArrayList;
import java.util.List;


public class DetallesVentaMapper {

    public  static List<DetallesVentaDto> toListDto (List<DetallesVenta> dv){

        List<DetallesVentaDto> detallesVentaListDto = new ArrayList<>();

        dv.forEach( listaDetalles -> detallesVentaListDto.add(toDto(listaDetalles)));


        return detallesVentaListDto;

    }

    public static DetallesVentaDto toDto (DetallesVenta dv){

        return DetallesVentaDto.builder()
                .id(dv.getId())
                .cantidad(dv.getCantidad())
                .precioUnitario(dv.getPrecioUnitario())
                .subTotal(dv.getSubTotal())
                .producto(ProductoMapper.toDto(dv.getProducto()))
                .build();
    }

    public  static DetallesVenta toEntity (DetallesVentaDto dv){

        return DetallesVenta.builder()
                //el id se asigna automaticamente en ele guardado
                .cantidad(dv.getCantidad())
                .precioUnitario(dv.getPrecioUnitario())
                //el sub total se asigna automaticamente antes de guardar la venta
                //el id de venta se asigna desde el service
                //producto se mapea y se asigna desde el servicio
                .build();


    }
}
