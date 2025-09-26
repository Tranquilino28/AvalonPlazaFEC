package org.softfriascorporations.avalonplazafec.entities.detalleventa.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;

import java.math.BigDecimal;

@Entity
@Table(name = "detalles_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetallesVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    private BigDecimal precioUnitario;

    private BigDecimal subTotal;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "venta_id")
    private Venta venta;

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
