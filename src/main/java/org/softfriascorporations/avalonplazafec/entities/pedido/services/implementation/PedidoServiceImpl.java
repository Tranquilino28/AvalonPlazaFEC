package org.softfriascorporations.avalonplazafec.entities.pedido.services.implementation;


import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.Producto.repositories.ProductoRepository;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.entities.DetallePedido;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.mappers.mappers.DetallePedidoMapper;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.softfriascorporations.avalonplazafec.entities.pedido.mappers.PedidoMapper;
import org.softfriascorporations.avalonplazafec.entities.pedido.repositories.PedidoRopository;
import org.softfriascorporations.avalonplazafec.entities.pedido.services.interfaces.PedidoService;

import org.softfriascorporations.avalonplazafec.entities.ventas.util.PedidoDtoUtilProcessor;
import org.softfriascorporations.avalonplazafec.util.UtillConversorTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    PedidoRopository pedidoRepository;
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    MaestraRepository maestraRepository;

    @Override
    public List<PedidoDto> findAll() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        if (pedidos.isEmpty()) {
            throw new EntityNotFoundException("sin pedidos registrados");
        }
        List<PedidoDto> pedidoDtoList = new ArrayList<>();

        pedidos.forEach(pedido -> {
            pedidoDtoList.add(PedidoDtoUtilProcessor.ProcessPedidoDto(pedido));
        });


        return pedidoDtoList;
    }

    @Override
    public PedidoDto findById(Long aLong) {

        return PedidoMapper.toDto(pedidoRepository.findById(aLong)
                .orElseThrow(() -> new EntityNotFoundException("no se ecnuentra el pedido"))
        );

    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }

    @Transactional
    @Override
    public PedidoDto save(PedidoDto pedidoDto) {

        /**
         * Se mapea la venta inicialmente
         * se crea una lista de la entidad venta
         * se usa forEach {
         * con la lista de detalles
         * se busca el producto en la base de datos
         * mapeado de los detalles
         * se mapean los detales y se le asigna el producto mapeado
         * se lista cada detalle y se asigna la venta en detalle
         * }
         * se mapea la venta y se añade los detalles listados
         */

        //se inicializa la entidad venta mapeando inicialmente
        Pedido pedido = PedidoMapper.toEntity(pedidoDto);

        Maestra metodoPago = maestraRepository.findById(pedidoDto.getMetodoPago().getId()).orElseThrow(
                () -> new EntityNotFoundException("metodo de pago no encontrado")
        );

        pedido.setMetodoPago(metodoPago);

        List<DetallePedido> detallePedidos = new ArrayList<>();


        pedidoDto.getDetalles().forEach(detalle -> {


                    Producto producto =
                            productoRepository.findById(detalle.getProducto().getId()).orElseThrow(() ->
                                    new EntityNotFoundException("Producto no encontrado con código: " + detalle.getProducto().getCodigoBarras())
                            );


                    if (producto.getStockDisponible() == null || producto.getStockDisponible() <= 0 || producto.getStockDisponible() < detalle.getCantidad()) {
                        throw new RuntimeException("verificar el stock disponible para las cantidades solicitadas");
                    }

                    producto.setStockDisponible(producto.getStockDisponible() - detalle.getCantidad());

                    //procesamiento de detalleventa
                    DetallePedido detalles = DetallePedidoMapper.toEntity(detalle);
                    detalles.setProducto(producto);

                    //Se asigna la venta en detallesVenta
                    detalles.setPedido(pedido);

                    //se listan los detallesVenta
                    detallePedidos.add(detalles);
                }
        );

        pedido.setDetalles(detallePedidos);

        Maestra estadoCarrito = maestraRepository.findByNombreCorto(pedidoDto.getEstadoPedido().getNombreCorto())
        .orElseThrow(() -> new EntityNotFoundException("carrito corrupto"));

        if (estadoCarrito.getNombreCorto().equals("CARR")){

            pedido.setEstado(estadoCarrito);
        }else{
            pedido.setEstado(maestraRepository.findByNombreCorto("PEND")
                    .orElseThrow(() -> new RuntimeException("no se puede realizar pedido")));
        }

        //retorna el pedido guardada
        Pedido pedidoGuardada  = pedidoRepository.save(pedido);



//***********************************************
/**
 * se realizan los mapeos correspondientes a productos, detalles y finalmente pedido
 */

        return PedidoMapper.toDto(pedidoGuardada);
    }

    @Override
    public PedidoDto findByCodigo(String codigo) {
        Pedido p = pedidoRepository.findByCodigoPedido(UtillConversorTypes.stringToUuid(codigo))
                .orElseThrow(() -> new EntityNotFoundException("no se ecnuentra el pedido"));
        return PedidoDtoUtilProcessor.ProcessPedidoDto(p);
    }



}
