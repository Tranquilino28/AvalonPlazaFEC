package org.softfriascorporations.avalonplazafec.entities.maestra.controllers;

import org.softfriascorporations.avalonplazafec.entities.maestra.dtos.MaestraDto;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;
import org.softfriascorporations.avalonplazafec.entities.maestra.mappers.MaestraMapper;
import org.softfriascorporations.avalonplazafec.entities.maestra.services.interfaces.MaestraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/maestra")
public class MaestraControllers {


    @Autowired
    MaestraService maestraService;



    @PostMapping("/saveAll")
    public ResponseEntity<Boolean> save(@RequestBody List<Maestra> maestraData) {

            maestraService.saveAll(maestraData);
            return ResponseEntity.ok(true);

    }

    @GetMapping("/all")
    public List<MaestraDto> findAll() {
        List<MaestraDto> maestraDtos = new ArrayList<>();
        maestraService.findAll().forEach(m ->
                maestraDtos.add(MaestraMapper.toDto(m))

        );


        return maestraDtos;
    }
}
