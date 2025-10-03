package org.softfriascorporations.avalonplazafec.entities.Producto.services.implementation;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.Producto.repositories.ProductoRepository;
import org.softfriascorporations.avalonplazafec.entities.Producto.services.interfaces.ProductoService;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private MaestraRepository maestraRepository;

    @Override
    public List<ProductoDto> findAll() {

        List<Producto> productos = productoRepository.findAll();

        if (productos.isEmpty()) {
            throw new RuntimeException("no se encontraron productos en la base de datos ");
        }
        List<ProductoDto> productoDtos = new ArrayList<>();

        productos.forEach(producto -> {productoDtos.add(ProductoMapper.toDto(producto));});

        return productoDtos;
    }

    @Override
    public ProductoDto findById(Long id) {


        Optional<Producto> producto = Optional.of(productoRepository.findById(id).get());

        if (!producto.isPresent()) {
            throw new EntityNotFoundException("no se encontro el producto");
        }
        return ProductoMapper.toDto(producto.get());
    }

    @Transactional
    @Override
    public ProductoDto save(ProductoDto pDto) {

        Optional.ofNullable(productoRepository.findByCodigoBarras(pDto.getCodigoBarras()))
                .ifPresent(producto -> {
                    throw new EntityExistsException("Producto existente en la base de datos");
                });

        Maestra categoria =   maestraRepository.findById(pDto.getCategoria().getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + pDto.getCategoria()));

        Maestra medida =  maestraRepository.findById(pDto.getMedida().getId())
                .orElseThrow(() -> new EntityNotFoundException("Medida no encontrada: " + pDto.getMedida()));

        Producto nuevo = ProductoMapper.toEntity(pDto);
        nuevo.setCategoria(categoria);
        nuevo.setMedida(medida);

        Producto guardado = productoRepository.save(nuevo);

        return ProductoMapper.toDto(guardado);
    }

    @Transactional
    @Override
    public Boolean deleteById(Long id) {

        productoRepository.deleteById(id);

        return true;
    }

    @Override
    public Boolean deleteByCodigoBarras(String codigoBarras) {

        Optional<Producto> producto = Optional.ofNullable(productoRepository.findByCodigoBarras(codigoBarras));
        if (!producto.isPresent()) {
            throw new EntityNotFoundException("no se encontro el producto");
        }
        return deleteById(producto.get().getId());
    }

    @Transactional
    @Override
    public Boolean ubdateByCodigoBarras(ProductoDto pDto) {
        Producto p = findByCodigoBarras(pDto.getCodigoBarras());

        Maestra categoria = maestraRepository.findById(pDto.getCategoria().getId())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada: " + pDto.getCategoria()));

        Maestra medida =  maestraRepository.findById(pDto.getMedida().getId())
                .orElseThrow(() -> new EntityNotFoundException("Medida no encontrada: " + pDto.getMedida()));

        p.setCategoria(categoria);
        p.setMedida(medida);
        p.setDescripcion(pDto.getDescripcion());
        p.setNombre(pDto.getNombre());
        p.setPrecio(pDto.getPrecio());
        p.setStockDisponible(pDto.getStockDisponible());

        try {
            productoRepository.save(p);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Error al guardar producto: " + e.getRootCause().getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error inesperado al guardar producto: " + e.getMessage());
        }
        return true;
    }

    @Override
    public Producto findByCodigoBarras(String codigoBarras) {
       return Optional.ofNullable(productoRepository.findByCodigoBarras(codigoBarras))
               .orElseThrow(() -> new EntityNotFoundException("No se encontró el producto con código de barras: " + codigoBarras));

    }
    @Transactional
    @Override
    public Boolean saveAll(List<ProductoDto> productos) {

           productos.forEach(this::save);
        return true ;
    }
}
