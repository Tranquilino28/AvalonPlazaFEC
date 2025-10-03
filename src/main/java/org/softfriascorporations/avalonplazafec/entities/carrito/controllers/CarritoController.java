package org.softfriascorporations.avalonplazafec.entities.carrito.controllers;

import org.softfriascorporations.avalonplazafec.entities.carrito.services.interfaces.CarritoService;
import org.softfriascorporations.avalonplazafec.entities.detallespedido.dtos.DetallesPedidoDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.mappers.MaestraMapper;
import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    CarritoService carritoService;


    @PostMapping("/add")
    public PedidoDto addCarrito(@RequestBody DetallesPedidoDto detalle) {

        return carritoService.addCarrito(detalle);
    }
    @PostMapping("/delete/{carritoId}/item/{itemId}")
    public PedidoDto deleteItem(@PathVariable Long carritoId, @PathVariable Long itemId) {

        return carritoService.deleteItem(carritoId, itemId);
    }

    @GetMapping("/all")
    public PedidoDto getCarrito() {

        return carritoService.getCarrito();
    }

    @PutMapping("/{carritoId}/metodoPago/{mtPgIdd}")
    public PedidoDto carritoToPedido(@PathVariable Long carritoId, @PathVariable Long mtPgIdd){

        return carritoService.carritoToPedido(carritoId, mtPgIdd);
    }
}
