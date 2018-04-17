package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PdvDTO;
import com.example.poslovna_informatika.services.PdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return new ResponseEntity<>(pdvService.getAllPdvs(), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PdvDTO> saveItem(@RequestBody PdvDTO pdvDTO) {
        return new ResponseEntity<>(pdvService.savePdvDto(pdvDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PdvDTO> updateItem(@RequestBody PdvDTO pdvDTO,
                                             @PathVariable("id") long id) {
        PdvDTO pdv = pdvService.updatePdv(pdvDTO, id);

        if (pdv == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(pdv, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (pdvService.deletePdv(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
