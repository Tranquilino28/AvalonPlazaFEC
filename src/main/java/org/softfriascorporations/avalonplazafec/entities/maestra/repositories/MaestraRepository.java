package org.softfriascorporations.avalonplazafec.entities.maestra.repositories;

import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestraRepository extends JpaRepository<Maestra, Long> {


    Maestra findByNombreCorto(String nombreCorto);
}
