package org.softfriascorporations.avalonplazafec.entities.Producto.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDto {


    private Long id;

    private String codigoBarras;

    private String nombre;

    private String descripcion;

    private BigDecimal precio;

    private MaestraDto categoria;

    private MaestraDto medida;

    private Integer stockDisponible;

}
