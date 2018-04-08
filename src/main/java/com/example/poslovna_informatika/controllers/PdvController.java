package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.MestoDTO;
import com.example.poslovna_informatika.dto.PdvDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.services.MestoService;
import com.example.poslovna_informatika.services.PdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of controller
 * Required services to be completed
 */
@RestController
@RequestMapping(value = "api/pdv")
public class PdvController {

    private PdvService pdvService;


    @Autowired
    public PdvController(PdvService pdvService) {
        this.pdvService = pdvService;
    }


    @GetMapping
    public ResponseEntity<List<PdvDTO>> getPdvs() {
        List<PDV> pdvs = pdvService.findAll();
        List<PdvDTO> pdvDTOS = new ArrayList<PdvDTO>();
        for (PDV pdv : pdvs) {
            pdvDTOS.add(new PdvDTO(pdv));
        }
        return new ResponseEntity<List<PdvDTO>>(pdvDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PdvDTO> saveItem(@RequestBody PdvDTO pdvDTO) {
        PDV pdv = new PDV(pdvDTO.getNaziv());

        pdv = pdvService.save(pdv);

        return new ResponseEntity<PdvDTO>(new PdvDTO(pdv), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PdvDTO> updateItem(@RequestBody PdvDTO pdvDTO,
                                                      @PathVariable("id") long id) {
        PDV pdv = pdvService.findOne(id);

        if (pdv == null) {
            return new ResponseEntity<PdvDTO>(HttpStatus.BAD_REQUEST);
        }
        pdv.setNaziv(pdvDTO.getNaziv());

        pdv = pdvService.save(pdv);

        return new ResponseEntity<PdvDTO>(new PdvDTO(pdv), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        PDV pdv = pdvService.findOne(id);
        if (pdv != null) {
            pdvService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
