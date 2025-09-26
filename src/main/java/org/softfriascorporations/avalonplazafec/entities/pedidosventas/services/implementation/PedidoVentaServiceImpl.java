package org.softfriascorporations.avalonplazafec.entities.pedidosventas.services.implementation;

import jakarta.persistence.EntityNotFoundException;
import org.softfriascorporations.avalonplazafec.entities.detalleventa.entities.DetallesVenta;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.repositories.MaestraRepository;
import org.softfriascorporations.avalonplazafec.entities.pedido.entities.Pedido;
import org.softfriascorporations.avalonplazafec.entities.pedido.repositories.PedidoRopository;
import org.softfriascorporations.avalonplazafec.entities.pedidosventas.services.interfaces.PedidoVentaService;
import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.softfriascorporations.avalonplazafec.entities.ventas.entities.Venta;
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

@Service
public class PedidoVentaServiceImpl implements PedidoVentaService {

    @Autowired
    private PedidoRopository pedidoRopository;


    @Autowired
    private MaestraRepository maestraRepository;
    @Autowired
    private VentaRepository ventaRepository;

    @Transactional
    @Override
    public VentaDto facturarPedidoVenta(String codigoPedido, String metodoPago) {
metodoPago = "NEQ";

        Pedido pedido = Optional.ofNullable(
                        pedidoRopository.findByCodigoPedido(UtillConversorTypes.stringToUuid(codigoPedido)))
                .orElseThrow(() -> new EntityNotFoundException("pedido invalido")
                );

        Maestra metodoPagoVenta = Optional.ofNullable(
                maestraRepository.findByNombreCorto(metodoPago)).orElseThrow(
                        () -> new EntityNotFoundException("metodo de pago invalido")
        );


        Venta venta = Venta.builder()
                .pedido(pedido)
                .build();

        List<DetallesVenta> detalleventa = new ArrayList<>();

        pedido.getDetalles().forEach(detalles -> {

            DetallesVenta detallesVenta = new DetallesVenta();

            detallesVenta.setCantidad(detalles.getCantidad());
            detallesVenta.setPrecioUnitario(detalles.getPrecioUnitario());
            detallesVenta.setProducto(detalles.getProducto());

            detallesVenta.setVenta(venta);
            detalleventa.add(detallesVenta);

        });







        Venta ventaGuardada ;


        try{

            venta.setEstado(maestraRepository.findByNombreCorto("FACT"));
            venta.setDetalles(detalleventa);
            venta.setMetodo_de_pago(metodoPagoVenta);


            ventaGuardada = ventaRepository.save(venta);
            pedido.setEstado(maestraRepository.findByNombreCorto("FIN"));

         }catch (EntityNotFoundException e){
            throw new EntityNotFoundException("No se puede concretar la venta : "+e.getMessage());

        }catch (RuntimeException e){
            throw new RuntimeException("No se puede concretar la venta : "+e.getMessage());

        };



        return  VentaDtoUtilProcessor.processVentaDto(ventaGuardada);
    }
}
