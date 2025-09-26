package org.softfriascorporations.avalonplazafec.entities.crudService;

import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;
import org.softfriascorporations.avalonplazafec.entities.usuarios.entities.Usuario;
import org.softfriascorporations.avalonplazafec.entities.usuarios.mappers.UsuarioMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GenericServiceImpl<T, ID> implements GenericService<T, ID> {

    protected abstract JpaRepository<T, ID> getRepository();

    @Override
    public T save(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public T findById(ID id) {

        Optional<T> t = getRepository().findById(id);
        if (!t.isPresent()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }

        return t.get();
    }

    public List<T> findAll() {

        List<T> t = getRepository().findAll();
        if (t.isEmpty()) {
            throw new RuntimeException("no se encuentran datos en la BD");
        }

        return t;
    }

    @Override
    public Boolean deleteById(ID id) {
        getRepository().deleteById(id);
        return true;
    }
}

