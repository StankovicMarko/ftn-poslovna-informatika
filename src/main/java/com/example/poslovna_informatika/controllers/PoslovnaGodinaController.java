package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PoslovnaGodinaDTO;
import com.example.poslovna_informatika.model.PoslovnaGodina;
import com.example.poslovna_informatika.services.PoslovnaGodinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/poslovna-godina")
public class PoslovnaGodinaController {

    private PoslovnaGodinaService poslovnaGodinaService;


    @Autowired
    public PoslovnaGodinaController(PoslovnaGodinaService poslovnaGodinaService) {
        this.poslovnaGodinaService = poslovnaGodinaService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<PoslovnaGodinaDTO> getPoslGodine(@PathVariable("id") long id) {
        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findOne(id);
        return new ResponseEntity<>(new PoslovnaGodinaDTO(poslovnaGodina), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PoslovnaGodinaDTO>> getPoslGodine() {
        return new ResponseEntity<>(poslovnaGodinaService.getAllPoslovnaGodina(), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PoslovnaGodinaDTO> saveItem(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO) {
        return new ResponseEntity<>(poslovnaGodinaService.savePoslovnaGodina(poslovnaGodinaDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PoslovnaGodinaDTO> updateItem(@RequestBody PoslovnaGodinaDTO poslovnaGodinaDTO,
                                                        @PathVariable("id") long id) {
        PoslovnaGodinaDTO pg = poslovnaGodinaService.updatePoslovnaGodina(poslovnaGodinaDTO, id);

        if (pg == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(pg, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (poslovnaGodinaService.deletePoslovnaGodina(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
