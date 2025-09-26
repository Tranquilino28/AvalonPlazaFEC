package org.softfriascorporations.avalonplazafec.entities.ventas.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDto {

    private UUID codigoVenta;

    private BigDecimal valorTotal;

     private LocalDateTime fechaVenta;

    private String metodo_de_pago;

    private List<DetallesVentaDto> detalles = new ArrayList<>();

}
