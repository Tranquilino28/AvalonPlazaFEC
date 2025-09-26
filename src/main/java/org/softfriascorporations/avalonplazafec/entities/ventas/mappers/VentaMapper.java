package org.softfriascorporations.avalonplazafec.entities.ventas.mappers;

import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;

public class VentaMapper {

    public static VentaDto toVentaDto(Venta venta) {
        return VentaDto.builder()
                //.id(venta.getId())
                .codigoVenta(venta.getCodigoVenta())
                .valorTotal(venta.getValorTotal())
                .fechaVenta(venta.getFechaVenta())
                .metodo_de_pago(venta.getMetodo_de_pago().getNombreLargo())
                //la lista de detallesventa se mapea y se asigna desde el servicio
                .build();

    }
    public static Venta toEntity(VentaDto venta) {
        return Venta.builder()
                //el id se asigna automaticamente en ele guardado
                //el codigo de la venta se asigna automaticamente
                .valorTotal(venta.getValorTotal())
                .fechaVenta(venta.getFechaVenta())

                //el metodo de pago se asigna desde el service
                //la lista de detallesventa se mapea y se asigna desde el servicio
                .build();

    }
}
