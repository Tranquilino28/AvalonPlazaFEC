package org.softfriascorporations.avalonplazafec.entities.ventas.controllers;

import org.softfriascorporations.avalonplazafec.entities.ventas.dtos.VentaDto;
import org.softfriascorporations.avalonplazafec.entities.ventas.services.interfaces.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping("/save")
    public VentaDto ventaSave(@RequestBody VentaDto ventaDto) {

            VentaDto respuestaVentaDto = ventaService.save(ventaDto);
            return respuestaVentaDto;
    }


    @GetMapping("/search/v1/{codigo}")
    public VentaDto ventaSearch(@PathVariable String codigo) {

       return ventaService.searchByCodigo(codigo);
    }


    @DeleteMapping("/suprimir/{codigo}")
    public ResponseEntity<String> ventaDelete(@PathVariable String codigo) {

        ventaService.deleteByCodigo(codigo);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
