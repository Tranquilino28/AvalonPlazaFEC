package org.softfriascorporations.avalonplazafec.entities.pedido.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.entities.DetallePedido;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_pedido", nullable = false, unique = true, updatable = false, columnDefinition = "uuid not null default gen_random_uuid()")
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID codigoPedido;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_estpedi", foreignKey = @ForeignKey(name = "fk_pedido_estado"))
    Maestra estado;

    @Column(nullable = false)
    private LocalDateTime fechaPedido;
    /*
        @ManyToOne(optional = false) // cada venta pertenece a un cliente
        @JoinColumn(name = "clie_id", nullable = false)
        private Cliente cliente;

        @ManyToOne
        @JoinColumn(name = "pedi_id", nullable = true)
        private Pedido pedido;
    */
    @ManyToOne
    @JoinColumn(name = "maes_metodopago",foreignKey = @ForeignKey(name = "fk_pedido_metodopago"))
    private Maestra metodoPago;

/*
    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
 */

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DetallePedido> detalles = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        this.fechaPedido = LocalDateTime.now();
        calcularValorTotal();
    }

    @PreUpdate
    public void preUpdate() {
        calcularValorTotal();
    }

    public void calcularValorTotal() {
        this.valorTotal = detalles.stream()
                .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addDetalle(DetallePedido detalle) {
        detalle.setPedido(this);
        this.detalles.add(detalle);
    }
}
