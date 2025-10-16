package org.softfriascorporations.avalonplazafec.entities.usuarios.services.implementation;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.softfriascorporations.avalonplazafec.entities.maestra.services.interfaces.MaestraService;
import org.softfriascorporations.avalonplazafec.entities.usuarios.dtos.UsuarioDto;
import org.softfriascorporations.avalonplazafec.entities.usuarios.entities.Usuario;
import org.softfriascorporations.avalonplazafec.entities.usuarios.mappers.UsuarioMapper;
import org.softfriascorporations.avalonplazafec.entities.usuarios.repositories.UsuarioRepository;
import org.softfriascorporations.avalonplazafec.entities.usuarios.services.interfaces.UsuarioService;
import org.softfriascorporations.avalonplazafec.util.PassSecure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepositpory;
@Autowired
    MaestraRepository maestraRepositpory;



    @Override
    public List<UsuarioDto> findAll() {

       List<Usuario> usuarios = usuarioRepositpory.findAll();
        if (usuarios.isEmpty()) {
           throw new RuntimeException("No hay usuarios disponibles");
        }

        List<UsuarioDto> usuarioDtos = new ArrayList<>();
        usuarios.forEach(u -> { usuarioDtos.add(UsuarioMapper.toDto(u)); });

        return usuarioDtos;
    }

    @Override
    public UsuarioDto findById(Long id) {

        Optional<Usuario> usuario = usuarioRepositpory.findById(id);
        if (!usuario.isPresent()) {
           throw new EntityNotFoundException("Usuario no encontrado");
        }

        return UsuarioMapper.toDto(usuario.get());
    }
    @Transactional
    @Override
    public UsuarioDto save(UsuarioDto dto) {
           Optional<Usuario> user = usuarioRepositpory.findByNombreUsuario(dto.getUserName());

           if (user.isPresent()) {

               throw new EntityExistsException("usuario ya existe");
           }

        Maestra rol = maestraRepositpory.findById(dto.getRole().getId())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        try {



            Usuario uEntity = UsuarioMapper.toEntity(dto);
            uEntity.setHashsalt(PassSecure.generateSalt());
            uEntity.setHashpassword(PassSecure.hashPassword(dto.getPassword(),uEntity.getHashsalt()));



            uEntity.setMaesRol(rol);

            return UsuarioMapper.toDto(usuarioRepositpory.save(uEntity));

        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar usuario: " + e.getRootCause().getMessage());
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("error al guardar usuario: " + e.getMessage());
        }
    }

    @Transactional
    @Override
    public Boolean deleteById(Long id) {
        /*
        Optional<Usuario> usuario = usuarioRepositpory.findById(id);

        if(!usuario.isPresent()) {
            throw new EntityNotFoundException("Usuario no se encuentra en la base de datos");
        }


         */
        usuarioRepositpory.deleteById(id);

        return true;
    }


    @Override
    public Page<Usuario> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return usuarioRepositpory.findAll(pageable);
    }

    @Override
    public Optional<Usuario> findByNombreUsuario(String nombreUsuario) {
      return Optional.of(usuarioRepositpory.findByNombreUsuario(nombreUsuario).orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

    }
}
