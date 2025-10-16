package org.softfriascorporations.avalonplazafec.entities.detallespedido.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;

import java.math.BigDecimal;

@Entity
@Table(name = "detallespedido")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subTotal;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "prod_id")
    private Producto producto;
/*
    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
*/

    @PrePersist
    @PreUpdate
    public void calcularSubTotal() {
        this.subTotal = this.precioUnitario.multiply(BigDecimal.valueOf(this.cantidad));
    }
}
