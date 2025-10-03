package org.softfriascorporations.avalonplazafec.entities.pedidosventas.controllers;


import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.pedidosventas.dtos.PedidoVentaDto;
import org.softfriascorporations.avalonplazafec.entities.pedidosventas.services.interfaces.PedidoVentaService;
import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidoVenta")
public class PedidoVentaController {

    @Autowired
    PedidoVentaService pedidoVentaService;

    @PostMapping("/facturar/{id}")
    public VentaDto facturarPedido(@PathVariable Long id, @RequestBody MaestraDto maestraId) {

    return pedidoVentaService.facturarPedidoVenta(id, maestraId.getId());
    }
}
