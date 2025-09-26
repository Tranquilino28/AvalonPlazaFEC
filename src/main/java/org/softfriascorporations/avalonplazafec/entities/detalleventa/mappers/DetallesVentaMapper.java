package org.softfriascorporations.avalonplazafec.entities.detalleventa.mappers;

import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.entities.DetallesVenta;


public class DetallesVentaMapper {

    public  static DetallesVentaDto toDto (DetallesVenta dv){

        return DetallesVentaDto.builder()
              //  .id(dv.getId())
                .cantidad(dv.getCantidad())
                .precioUnitario(dv.getPrecioUnitario())
                .subTotal(dv.getSubTotal())
                //producto se mapea y se asigna desde el servicio
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
