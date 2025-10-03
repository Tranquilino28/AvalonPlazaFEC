package org.softfriascorporations.avalonplazafec.entities.Producto.mappers;

import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.maestra.mappers.MaestraMapper;

public class ProductoMapper {

    public static ProductoDto toDto(Producto producto) {

        return ProductoDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .categoria(MaestraMapper.toDto(producto.getCategoria()))
                .medida(MaestraMapper.toDto(producto.getMedida()))
                .precio(producto.getPrecio())
                .codigoBarras(producto.getCodigoBarras())
                .stockDisponible(producto.getStockDisponible())
                .build();
    }

    public static Producto toEntity(ProductoDto dto) {
       return Producto.builder()
                //.id(dto.getId())
                .codigoBarras(dto.getCodigoBarras())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                //la categoria se asigna desde eel servicio
                //la medida se asigna desde el servivio
                .stockDisponible(dto.getStockDisponible())
                .build();

    }
}
