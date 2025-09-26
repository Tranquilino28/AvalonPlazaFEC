package org.softfriascorporations.avalonplazafec.entities.pedido.controllers;

import org.softfriascorporations.avalonplazafec.entities.pedido.dtos.PedidoDto;
import org.softfriascorporations.avalonplazafec.entities.pedido.services.interfaces.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoControllers {

    @Autowired
    private PedidoService pedidoService;


    @PostMapping("/save")
    public PedidoDto save(@RequestBody PedidoDto pedidoDto) {

        return pedidoService.save(pedidoDto);
    }
}
