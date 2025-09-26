package org.softfriascorporations.avalonplazafec.entities.maestra.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.crudService.GenericService;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MaestraService extends GenericService<Maestra,Long> {


    List<Maestra> saveAll(List<Maestra> dtos);

    List<Maestra> saveAllVerify(List<Maestra> dtos);

    Maestra findByNombreCorto(String nombreCorto);
}
