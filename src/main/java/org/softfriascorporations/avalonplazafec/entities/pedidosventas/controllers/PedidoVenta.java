package org.softfriascorporations.avalonplazafec.entities.pedidosventas.controllers;


import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.services.interfaces.PedidoService;
import org.softfriascorporations.avalonplazafec.entities.pedidosventas.dtos.PedidoVentaDto;
import org.softfriascorporations.avalonplazafec.entities.pedidosventas.services.interfaces.PedidoVentaService;
import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidoVenta")
public class PedidoVenta {

    @Autowired
    PedidoVentaService pedidoVentaService;

    @PostMapping("/facturarPedido")
    public VentaDto facturarPedido(@RequestBody PedidoVentaDto pedioVentaDto) {

    return pedidoVentaService.facturarPedidoVenta(
            pedioVentaDto.getCodigoPedido()
            , pedioVentaDto.getMetdoPago());
    }
}
