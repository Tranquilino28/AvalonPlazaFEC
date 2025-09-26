package org.softfriascorporations.avalonplazafec.entities.ventas.util;

import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.mappers.DetallesVentaMapper;
import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;
import org.softfriascorporations.avalonplazafec.entities.ventas.mappers.VentaMapper;

import java.util.ArrayList;
import java.util.List;

public class VentaDtoUtilProcessor {
    public static VentaDto processVentaDto(Venta v) {

        List<DetallesVentaDto> detallesVentaDto = new ArrayList<>();

        v.getDetalles().forEach(detalle -> {
            ProductoDto productoDto = ProductoMapper.toDto(detalle.getProducto());
            DetallesVentaDto detalleDto = DetallesVentaMapper.toDto(detalle);
            detalleDto.setProducto(productoDto);

            detallesVentaDto.add(detalleDto);
        });

        VentaDto ventaDto = VentaMapper.toVentaDto(v);
        ventaDto.setDetalles(detallesVentaDto);

        return ventaDto;

    }
}
