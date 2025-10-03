package org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos;

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
public class DetallesPedidoDto {

private Long id;
    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subTotal;


    private ProductoDto producto;
}
