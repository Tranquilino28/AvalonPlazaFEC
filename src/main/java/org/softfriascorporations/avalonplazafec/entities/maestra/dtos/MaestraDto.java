package org.softfriascorporations.avalonplazafec.entities.maestra.dtos;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MaestraDto {

    private Long id;

    private String nombreLargo;

    private String nombreCorto;
}
