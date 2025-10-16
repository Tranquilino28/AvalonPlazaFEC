package org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.services.implementation;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.dtos.EmpresaDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.mappers.EmpresaMapper;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.repositories.EmpresaRepository;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.services.interfaces.EmpresaService;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.mappers.SucursalMapper;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private MaestraRepository maestraRepository;


    @Override
    public List<EmpresaDto> findAll() {


        List<Empresa> empresaList = empresaRepository.findAll();

        if (empresaList.isEmpty()) {
            throw new EntityNotFoundException("no tiene empresas e el sistema");
        }
        List<EmpresaDto> empresasDtoList = new ArrayList<>();
            empresaList.forEach(

                    eList -> empresasDtoList.add(EmpresaMapper.toDto(eList))
            );

        return empresasDtoList;
    }

    @Override
    public EmpresaDto findById(Long aLong) {
        return EmpresaMapper.toDto(
                empresaRepository.findById(aLong)
                .orElseThrow(() -> new EntityNotFoundException("la empresa no existe en la base de datos"))
        );
    }

    @Transactional
    @Override
    public EmpresaDto save(EmpresaDto empresaDto) {

        if (empresaRepository.existsByNit(empresaDto.getNit())) {

            throw new EntityExistsException("el nit corresponde a una empresa ya existente en el sistema");
        }
        if (empresaDto.getSucursales() == null || empresaDto.getSucursales().isEmpty()) {
            throw new IllegalArgumentException("Debe registrar al menos una sucursal para la empresa.");
        }

        Maestra estadoActivo = maestraRepository.findByNombreCorto("ACT")
                .orElseThrow(() -> new EntityNotFoundException("no puede crear la empresa"));


        System.out.println(empresaDto);


        Empresa empresa = EmpresaMapper.toEntity(empresaDto);

        empresa.setEstado(estadoActivo);


        empresa.getSucursales().forEach( s -> {
            s.setEmpresa(empresa);
            s.setEstado(estadoActivo);
            s.setPrincipal(false);
        });

        //se marca la priemra sucursal creada como la primera
        if (!empresa.getSucursales().isEmpty()) {
            empresa.getSucursales().get(0).setPrincipal(true);
        }


        Empresa eGuardada;
        try {
            return EmpresaMapper.toDto(empresaRepository.save(empresa));
        }catch (Exception e) {
           throw new IllegalArgumentException("no se puede guardar la empresa " + e.getMessage());
        }

    }

    @Override
    public Boolean deleteById(Long aLong) {

        maestraRepository.findById(aLong).orElseThrow(() -> new EntityNotFoundException("la empresa no existe en la base de datos"));

         empresaRepository.deleteById(aLong);

        return true;
    }

    @Override
    public EmpresaDto addSucursal(Long empresaId , SucursalDto sDto) {

        Empresa empresa = empresaRepository.findById(empresaId)
                .orElseThrow(() -> new EntityNotFoundException("la empresa no existe en el sistema"));

        boolean existe = empresa.getSucursales().stream()
                .anyMatch(s -> s.getNombre().equalsIgnoreCase(sDto.getNombre()));

        if (existe) {
            throw new EntityExistsException("Ya existe una sucursal con ese nombre en la empresa");
        }
        Maestra estadoActivo = maestraRepository.findByNombreCorto("ACT")
                .orElseThrow(() -> new EntityNotFoundException("no puede añadir la sucursal"));


        Sucursal sucursal = SucursalMapper.toEntity(sDto);
        sucursal.setPrincipal(false);
        sucursal.setEstado(estadoActivo);


        empresa.addSucursal(sucursal);


        return EmpresaMapper.toDto( empresaRepository.save(empresa));

    }
}
