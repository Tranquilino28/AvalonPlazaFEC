package org.softfriascorporations.avalonplazafec.entities.pedido.dtos;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.entities.DetallePedido;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PedidoDto {

    private Long id;

    private UUID codigoPedido;

    private BigDecimal valorTotal;

    private LocalDateTime fechaPedido;

    private String metodo_de_pago;

    private List<DetallesPedidoDto> detalles = new ArrayList<>();

}
