package org.softfriascorporations.avalonplazafec.entities.ventas.services.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.Producto.entities.Producto;
import org.softfriascorporations.avalonplazafec.entities.Producto.mappers.ProductoMapper;
import org.softfriascorporations.avalonplazafec.entities.Producto.repositories.ProductoRepository;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.dtos.DetallesVentaDto;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.entities.DetallesVenta;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.mappers.DetallesVentaMapper;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.softfriascorporations.avalonplazafec.entities.pedido.repositories.PedidoRopository;
import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;
import org.softfriascorporations.avalonplazafec.entities.ventas.mappers.VentaMapper;
import org.softfriascorporations.avalonplazafec.entities.ventas.repositories.VentaRepository;
import org.softfriascorporations.avalonplazafec.entities.ventas.services.interfaces.VentaService;
import org.softfriascorporations.avalonplazafec.entities.ventas.util.VentaDtoUtilProcessor;
import org.softfriascorporations.avalonplazafec.util.UtillConversorTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    MaestraRepository maestraRepository;
    @Autowired
    PedidoRopository pedidoRopository;



    @Override
    public List<VentaDto> findAll() {
        return List.of();
    }

    @Override
    public VentaDto findById(Long id) {
        Optional.ofNullable(ventaRepository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("venta no encontrada: "));

        return null;
    }

    @Transactional
    @Override
    public VentaDto save(VentaDto ventaDto) {

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
        Venta venta = VentaMapper.toEntity(ventaDto);


        List<DetallesVenta> detallesVenta = new ArrayList<>();


        ventaDto.getDetalles().forEach(detalle -> {


                    Producto producto = Optional.ofNullable(
                            productoRepository.findByCodigoBarras(detalle.getProducto().getCodigoBarras())
                    ).orElseThrow(() ->
                            new EntityNotFoundException("Producto no encontrado con código: " + detalle.getProducto().getCodigoBarras())
                    );

                    if (producto.getStockDisponible() == null || producto.getStockDisponible() <= 0 || producto.getStockDisponible() < detalle.getCantidad()) {
                        throw new RuntimeException("verificar el stock disponible para las cantidades solicitadas");
                    }

                    producto.setStockDisponible(producto.getStockDisponible() - detalle.getCantidad());

                    //procesamiento de detalleventa
            DetallesVenta detalles = DetallesVentaMapper.toEntity(detalle);
            detalles.setProducto(producto);

            //Se asigna la venta en detallesVenta
            detalles.setVenta(venta);

            //se listan los detallesVenta
            detallesVenta.add(detalles);
        }
        );

        venta.setDetalles(detallesVenta);

        venta.setMetodo_de_pago(maestraRepository.findByNombreCorto(ventaDto.getMetodo_de_pago()));

        venta.setEstado(
                Optional.ofNullable(maestraRepository.findByNombreCorto("FACT"))
                        .orElseThrow(() -> new RuntimeException("no se puede realizar la venta"))

        );
        //retorna la venta guardada
        Venta ventaGuardada  = ventaRepository.save(venta);


//***********************************************
/**
 * se realizan los mapeos correspondientes a productos, detalles y finalmente venta
 */

        List<DetallesVentaDto> detallesVentaDto = new ArrayList<>();

        venta.getDetalles().forEach(detalle -> {
            DetallesVentaDto detalleVentaDtos = DetallesVentaMapper.toDto(detalle);
            detalleVentaDtos.setProducto(ProductoMapper.toDto(detalle.getProducto()));

            detallesVentaDto.add(detalleVentaDtos);});

        VentaDto ventaDtoGuardado = VentaMapper.toVentaDto(ventaGuardada);
        ventaDtoGuardado.setDetalles(detallesVentaDto);


        return ventaDtoGuardado;
    }

    @Transactional
    @Override
    public Boolean deleteById(Long aLong) {

        return null;
    }

    @Override
    public VentaDto searchByCodigo(String codigo) {
        UUID codigoVentaUUID = UtillConversorTypes.stringToUuid(codigo);

       Venta v = ventaRepository.findByCodigoVenta(codigoVentaUUID)
               .orElseThrow(() -> new EntityNotFoundException("venta no encontrada: "));

       return VentaDtoUtilProcessor.processVentaDto(v);
    }

    @Override
    public Boolean deleteByCodigo(String codigo) {

        Venta v = ventaRepository.findByCodigoVenta(UUID.fromString(codigo))
                .orElseThrow(() -> new EntityNotFoundException("venta no encontrada: "));

        ventaRepository.delete(v);

        return true;
    }

    @Override
    public VentaDto facturarPedidoByCodigo(String codigoPedido) {

        Pedido p = pedidoRopository.findByCodigoPedido(UtillConversorTypes.stringToUuid(codigoPedido));


        return null;

    }
}
