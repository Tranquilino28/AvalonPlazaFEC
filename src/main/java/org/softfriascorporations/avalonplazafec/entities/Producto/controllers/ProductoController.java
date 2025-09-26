package org.softfriascorporations.avalonplazafec.entities.Producto.controllers;

import org.softfriascorporations.avalonplazafec.entities.Producto.dtos.ProductoDto;
import org.softfriascorporations.avalonplazafec.entities.Producto.services.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping("/save")
    public boolean ProductoSave(@RequestBody ProductoDto productoDto) {

        System.out.println(productoDto);
        productoService.save(productoDto);
        return true;
    }

    @PutMapping("/update")
    public boolean ubdateProducto(@RequestBody ProductoDto productoDto) {

        productoService.ubdateByCodigoBarras(productoDto);
        return true;
    }
    @PostMapping("/save/all")
    public boolean ProductosSaveAll(@RequestBody List<ProductoDto> productoDto) {

        productoService.saveAll(productoDto);
        return true;
    }
}
