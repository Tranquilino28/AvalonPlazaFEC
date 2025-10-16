package org.softfriascorporations.avalonplazafec.entities.maestra.repositories;

import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaestraRepository extends JpaRepository<Maestra, Long> {


    Optional<Maestra> findByNombreCorto(String nombreCorto);
}
