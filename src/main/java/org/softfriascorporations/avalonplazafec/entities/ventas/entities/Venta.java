package org.softfriascorporations.avalonplazafec.entities.ventas.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.entities.DetallesVenta;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_venta", nullable = false, unique = true, updatable = false, columnDefinition = "uuid not null default gen_random_uuid()")
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID codigoVenta;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;
    /*
        @ManyToOne(optional = false) // cada venta pertenece a un cliente
        @JoinColumn(name = "clie_id", nullable = false)
        private Clien cliente;
    */
        @ManyToOne
        @JoinColumn(name = "pedi_id", foreignKey = @ForeignKey(name = "fk_venta_pedido"))
        private Pedido pedido;

    @ManyToOne(optional = false)
    @JoinColumn(name = "maes_metodopago")
    private Maestra metodoPago;

    @ManyToOne()
    @JoinColumn(name = "maes_estventa", foreignKey = @ForeignKey(name = "fk_venta_estado"))
    Maestra estado;

/*
    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;
 */

    ///nota: en el repository Cargarlas desde el repositorio con JOIN FETCH o @EntityGraph.
    @JsonManagedReference
    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @Builder.Default
    private List<DetallesVenta> detalles = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        this.fechaVenta = LocalDateTime.now();
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

    public void addDetalle(DetallesVenta detalle) {
        detalle.setVenta(this);
        this.detalles.add(detalle);
    }
}
