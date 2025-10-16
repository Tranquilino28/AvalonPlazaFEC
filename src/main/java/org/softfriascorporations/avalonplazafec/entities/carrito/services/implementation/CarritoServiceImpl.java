package org.softfriascorporations.avalonplazafec.entities.carrito.services.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.Producto.repositories.ProductoRepository;
import org.softfriascorporations.avalonplazafec.entities.Producto.services.interfaces.ProductoService;
import org.softfriascorporations.avalonplazafec.entities.carrito.services.interfaces.CarritoService;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.entities.DetallePedido;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.mappers.mappers.DetallePedidoMapper;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.repositories.DetallePedidoRepository;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.mappers.DetallesVentaMapper;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.softfriascorporations.avalonplazafec.entities.pedido.mappers.PedidoMapper;
import org.softfriascorporations.avalonplazafec.entities.pedido.repositories.PedidoRopository;
import org.softfriascorporations.avalonplazafec.entities.pedido.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    PedidoRopository pedidoRepository;
    @Autowired
    MaestraRepository maestraRepository;
    @Autowired
ProductoRepository productoRepository;
    @Autowired
    DetallePedidoRepository DetallePedidoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Transactional
    @Override
    public PedidoDto addCarrito(DetallesPedidoDto detalle) {
        Pedido pedidoEntity = new Pedido();
       try {
           Maestra estadoCarrito = maestraRepository.findByNombreCorto("CARR")
                   .orElseThrow(() -> new EntityNotFoundException("recurso no encontrado"));

           Optional<Pedido> pedido = pedidoRepository.findByEstado(estadoCarrito);

           if (pedido.isPresent()) {


               pedidoEntity = pedido.get();

               DetallePedido detalleEntity = DetallePedidoMapper.toEntity(detalle);
               Producto productoEntity = productoRepository.findById(detalle.getProducto().getId()).get();


               detalleEntity.setProducto(productoEntity);
               detalleEntity.setPedido(pedidoEntity);
               pedidoEntity.getDetalles().add(detalleEntity);

               pedidoEntity.calcularValorTotal();

               pedidoRepository.save(pedidoEntity);
               return PedidoMapper.toDto(pedidoEntity);
           } else {

               Producto productoEntity = productoRepository.findById(detalle.getProducto().getId())
                       .orElseThrow(() -> new EntityNotFoundException("el producto no se encuentra en la base de datos"));


                   List<DetallePedido> detallesPedido = new ArrayList<>();


                   DetallePedido detalleEntity = DetallePedidoMapper.toEntity(detalle);


                   detalleEntity.setProducto(productoEntity);


                   detalleEntity.setPedido(pedidoEntity);

                   pedidoEntity.getDetalles().add(detalleEntity);

                   pedidoEntity.setEstado(estadoCarrito);

                   pedidoEntity.calcularValorTotal();

                   pedidoEntity = pedidoRepository.save(Pedido.builder()
                           .estado(estadoCarrito)
                           .detalles(detallesPedido)
                           .build()
                   );

                   return PedidoMapper.toDto(pedidoEntity);

           }
       }catch (RuntimeException e) {
           e.printStackTrace();


       }
       return null;
    }


    @Override
    public Optional<Pedido> searchCarrito() {
        Maestra maestra = maestraRepository.findByNombreCorto("CARR").orElseThrow(() -> new EntityNotFoundException("recurso no encontrado"));

        return pedidoRepository.findByEstado(maestra);
    }

    @Transactional
    @Override
    public PedidoDto deleteItem(Long carritoId, Long itemId) {

        Pedido pedidoEntity = pedidoRepository.findById(carritoId)
                .orElseThrow(() -> new EntityNotFoundException("el carrito es corrupto"));

        DetallePedido detallesEntity = detallePedidoRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("el item es corrupto"));

        if(!detallesEntity.getPedido().getId().equals(pedidoEntity.getId())){
            throw new IllegalArgumentException("el item no pertenece a su carrito");

        }

        pedidoEntity.getDetalles().remove(detallesEntity);

        detallePedidoRepository.delete(detallesEntity);

        pedidoEntity.calcularValorTotal();

       return  PedidoMapper.toDto(pedidoRepository.save(pedidoEntity));
    }

    @Override
    public PedidoDto getCarrito() {


        return PedidoMapper.toDto(searchCarrito().orElseThrow(
                () -> new EntityNotFoundException("no se puede obtener el carrito")
        ));
    }

    @Transactional
    @Override
    public PedidoDto carritoToPedido(Long carritoId,Long metodoPagoId) {

        Pedido carrito = pedidoRepository.findById(carritoId)
                .orElseThrow(() -> new EntityNotFoundException("el carrito es corrupto"));


        if (carrito.getEstado().getNombreCorto().equals("CARR")) {

            Maestra estadoPedidoPendiente;
            try {
                estadoPedidoPendiente = maestraRepository.findByNombreCorto("PEND")
                        .orElseThrow(() -> new EntityNotFoundException("recurso no encontrado"));
            }catch (EntityNotFoundException e) {
                throw new EntityNotFoundException("no se puede realizar el pedido");
            }
            Maestra metodoPago = maestraRepository.findById(metodoPagoId)
                    .orElseThrow(() -> new EntityNotFoundException("metodo de pago no valido"));

            carrito.setEstado(estadoPedidoPendiente);
            carrito.setMetodoPago(metodoPago);
            carrito.calcularValorTotal();
            carrito.prePersist();


        }

        return PedidoMapper.toDto(pedidoRepository.save(carrito));
    }


}

