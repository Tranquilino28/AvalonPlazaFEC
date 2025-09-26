package org.softfriascorporations.avalonplazafec.entities.Producto.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codigoBarras;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private BigDecimal precio;

    @ManyToOne()
    @JoinColumn(name = "maes_categoria", foreignKey = @ForeignKey(name = "fk_producto_categoria"))
    private Maestra categoria;

    @ManyToOne()
    @JoinColumn(name = "maes_medida", foreignKey = @ForeignKey(name = "fk_producto_medida"))
    private Maestra medida;

    private Integer stockDisponible;
/*
    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id")
    private Empresa empresa;

 */



}