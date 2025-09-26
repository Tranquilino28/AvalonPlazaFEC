package org.softfriascorporations.avalonplazafec.entities.crudService;


import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;

import java.util.List;

public interface GenericService<T, ID> {

    // Lista todos los registros de cualquier tipo de entidad
    List<T> findAll();

    // Búsqueda por ID, usando Optional para manejar respuestas vacías
    T findById(ID id);

    // Guardar una entidad genérica
    T save(T t);


    // Eliminar por ID
    Boolean deleteById(ID id);
}