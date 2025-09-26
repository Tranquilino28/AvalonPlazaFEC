package org.softfriascorporations.avalonplazafec.entities.Producto.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {


    private String codigoBarras;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private String categoria;

    private String medida;

    private Integer stockDisponible;

}
