package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.FakturaDTO;
import com.example.poslovna_informatika.services.FakturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/faktura")
public class FakturaController {

    private FakturaService fakturaService;

    @Autowired
    public FakturaController(FakturaService fakturaService) {
        this.fakturaService = fakturaService;
    }

    @GetMapping
    public ResponseEntity<List<FakturaDTO>> getAllFakture(Pageable pageable) {
        return new ResponseEntity<>(fakturaService.getFakture(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<FakturaDTO>> getFakture(@PathVariable("id") long id, Pageable pageable) {
        return new ResponseEntity<>(fakturaService.getFaktureByPreduzeceId(id, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/partner/{id}")
    public ResponseEntity<List<FakturaDTO>> getPartnerFakture(@PathVariable("id") long id) {
        return new ResponseEntity<>(fakturaService.getFaktureByPartnerId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<FakturaDTO> getFaktura(@PathVariable("id") long id) {
        return new ResponseEntity<>(fakturaService.getFaktura(id), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<FakturaDTO> saveItem(@RequestBody FakturaDTO fakturaDTO) {
        return new ResponseEntity<>(fakturaService.saveFakturaDTO(fakturaDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<FakturaDTO> updateItem(@RequestBody FakturaDTO fakturaDTO, @PathVariable("id") long id) {
        FakturaDTO f = fakturaService.updateFakturaDTO(fakturaDTO, id);

        if (f == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(f, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (fakturaService.deleteFaktura(id)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
