package org.softfriascorporations.avalonplazafec.entities.maestra.mappers;

import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

public class MaestraMapper {

    public static MaestraDto toDto(Maestra m){

        if(m==null){
            return null;
        }

        return MaestraDto.builder()
                .id(m.getId() != null ? m.getId():null)
                .nombreLargo(m.getNombreLargo())
                .nombreCorto(m.getNombreCorto())
                .build();
    }

    public static Maestra toEntity(MaestraDto mDto){

        return Maestra.builder()
                .id(mDto.getId())
                .nombreLargo(mDto.getNombreLargo())
                .nombreCorto(mDto.getNombreCorto())
                .build();
    }


}
