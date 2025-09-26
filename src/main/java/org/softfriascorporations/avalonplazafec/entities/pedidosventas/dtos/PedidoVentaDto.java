package org.softfriascorporations.avalonplazafec.entities.pedidosventas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PedidoVentaDto {
    private String codigoPedido;
    private String metdoPago;
}
