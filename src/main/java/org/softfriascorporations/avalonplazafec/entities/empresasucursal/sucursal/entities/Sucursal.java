package org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

import java.util.UUID;

@Entity
@Table(name = "sucursal",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"codigo_sucursal", "empr_id"})
        })
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_sucursal", nullable = false)
    private String codigoSucursal;

    private String nombre;

    private String direccion;

    private String telefono;

    @Column(nullable = false)
    private boolean principal = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maes_estado", foreignKey = @ForeignKey(name = "fk_sucursal_estado"))
    private Maestra estado;

    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "empr_id", foreignKey = @ForeignKey(name = "fk_sucursal_empresa"))
    private Empresa empresa;

    @PrePersist
    public void generarCodigoSucursal() {
        if (this.codigoSucursal == null || this.codigoSucursal.isBlank()) {
            this.codigoSucursal = generarCodigoUnico();
        }
    }

    private String generarCodigoUnico() {
        // Podrías usar un UUID corto o una lógica más personalizada
        return "SUC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
