package org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallesVentaDto {


    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subTotal;


    private ProductoDto producto;
}
