package org.softfriascorporations.avalonplazafec.entities.Producto.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.crudService.GenericService;

import java.util.List;

public interface ProductoService extends GenericService<ProductoDto,Long> {
    Boolean deleteByCodigoBarras(String codigoBarras);
    Boolean ubdateByCodigoBarras(ProductoDto p);
    Producto findByCodigoBarras (String codigoBarras);

    Boolean saveAll(List<ProductoDto> productos);
}
