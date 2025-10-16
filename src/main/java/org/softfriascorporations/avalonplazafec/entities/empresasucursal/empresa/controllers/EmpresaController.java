package org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.controllers;

import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.dtos.EmpresaDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities.Empresa;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.services.interfaces.EmpresaService;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.dtos.SucursalDto;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;


    @PostMapping("/save")
    public EmpresaDto save(@RequestBody EmpresaDto empresaDto) {

        System.out.println("empresa recibida " + empresaDto);
        return empresaService.save(empresaDto);
    }

    @GetMapping("/all")
    public List<EmpresaDto> getAll() {

        return empresaService.findAll();
    }

    @DeleteMapping("/suprimir/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        empresaService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    @PostMapping("/{id}/sucursal")
    public EmpresaDto updateSucursal(@PathVariable Long id, @RequestBody SucursalDto sDto) {

        return empresaService.addSucursal(id, sDto);


    }
    @GetMapping("/{id}")
    public EmpresaDto findById(@PathVariable Long id) {
        return empresaService.findById(id);
    }

}
