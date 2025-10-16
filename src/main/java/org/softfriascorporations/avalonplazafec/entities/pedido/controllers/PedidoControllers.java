package org.softfriascorporations.avalonplazafec.entities.pedido.controllers;

import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoControllers {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping("/save")
    public PedidoDto save(@RequestBody PedidoDto pedidoDto) {

        return pedidoService.save(pedidoDto);
    }

    @GetMapping("/{id}")
    public PedidoDto getPedido(@PathVariable Long id) {

        return pedidoService.findById(id);
    }
}
