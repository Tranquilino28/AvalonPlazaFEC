package org.softfriascorporations.avalonplazafec.entities.ventas.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.crudService.GenericService;
import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;

public interface VentaService extends GenericService<VentaDto, Long> {
    VentaDto searchByCodigo(String codigo);

    Boolean deleteByCodigo(String codigo);

    VentaDto facturarPedidoByCodigo(String codigoPedido);
}
