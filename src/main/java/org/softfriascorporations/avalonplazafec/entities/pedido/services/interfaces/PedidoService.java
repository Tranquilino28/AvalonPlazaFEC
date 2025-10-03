package org.softfriascorporations.avalonplazafec.entities.pedido.services.interfaces;

import org.softfriascorporations.avalonplazafec.entities.crudService.GenericService;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;

public interface PedidoService extends GenericService<PedidoDto, Long> {



    PedidoDto findByCodigo(String codigo);


}
