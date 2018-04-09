package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PoslovnaGodinaDTO;
import com.example.poslovna_informatika.model.PoslovnaGodina;
import com.example.poslovna_informatika.services.PoslovnaGodinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/poslovna-godina")
public class PoslovnaGodinaController {

    private PoslovnaGodinaService poslovnaGodinaService;


    @Autowired
    public PoslovnaGodinaController(PoslovnaGodinaService poslovnaGodinaService) {
        this.poslovnaGodinaService = poslovnaGodinaService;
    }


    @GetMapping
    public ResponseEntity<List<PoslovnaGodinaDTO>> getPoslGodine() {
        List<PoslovnaGodina> poslovnaGodinas = poslovnaGodinaService.findAll();
        List<PoslovnaGodinaDTO> poslovnaGodinaDTOS = new ArrayList<PoslovnaGodinaDTO>();
        for (PoslovnaGodina pg : poslovnaGodinas) {
            poslovnaGodinaDTOS.add(new PoslovnaGodinaDTO(pg));
        }
        return new ResponseEntity<List<PoslovnaGodinaDTO>>(poslovnaGodinaDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PoslovnaGodinaDTO> saveItem(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO) {
        PoslovnaGodina pg = new PoslovnaGodina(poslovnaGodinaDTO.getGodina(), poslovnaGodinaDTO.isZakljucena());

        pg = poslovnaGodinaService.save(pg);
        return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(pg), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PoslovnaGodinaDTO> updateItem(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO,
                                                        @PathVariable("id") long id) {
        PoslovnaGodina pg = poslovnaGodinaService.findOne(id);

        if (pg == null) {
            return new ResponseEntity<PoslovnaGodinaDTO>(HttpStatus.BAD_REQUEST);
        }
        pg.setGodina(poslovnaGodinaDTO.getGodina());
        pg.setZakljucena(poslovnaGodinaDTO.isZakljucena());

        pg = poslovnaGodinaService.save(pg);

        return new ResponseEntity<PoslovnaGodinaDTO>(new PoslovnaGodinaDTO(pg), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        PoslovnaGodina pg = poslovnaGodinaService.findOne(id);
        if (pg != null) {
            poslovnaGodinaService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
