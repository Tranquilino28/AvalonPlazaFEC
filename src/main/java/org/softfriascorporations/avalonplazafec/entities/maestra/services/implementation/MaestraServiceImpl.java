package org.softfriascorporations.avalonplazafec.entities.maestra.services.implementation;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.softfriascorporations.avalonplazafec.entities.maestra.services.interfaces.MaestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MaestraServiceImpl implements MaestraService {

    @Autowired
    MaestraRepository maestraRepository;


    @Override
    public List<Maestra> findAll() {

        return maestraRepository.findAll();
    }

    @Override
    public Maestra findById(Long id) {
        Optional<Maestra> maestra = maestraRepository.findById(id);
        if (!maestra.isPresent()) {
            throw  new EntityNotFoundException("Maestra no encontrada");
        }
        return maestra.get();
    }

    @Transactional
    @Override
    public Maestra save(Maestra entity) {
        return null;
    }

    @Transactional
    @Override
    public Boolean deleteById(Long id) {
        return true;
    }


    public Page<Maestra> findAllPageable(int pagina, int tamaño) {
        Pageable pageable = PageRequest.of(pagina, tamaño);
        return maestraRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public List<Maestra> saveAll(List<Maestra> maestraList){

        try{
            return maestraRepository.saveAll(maestraList);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("error al guardar los datos de las maestras"+ e.getMessage());
        }catch (RuntimeException e){
            throw new RuntimeException("no se pudo guardar los datos" + e.getMessage());
        }
    }

    @Override
    public List<Maestra> saveAllVerify(List<Maestra> dtos) {
return null;
    }

    @Override
    public Maestra findByNombreCorto(String nombreCorto) {
        return Optional.ofNullable(maestraRepository.findByNombreCorto(nombreCorto)).orElseThrow();
    }


}
