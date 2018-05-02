package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.StavkaCenovnikaDTO;
import com.example.poslovna_informatika.dto.StavkaFaktureDTO;
import com.example.poslovna_informatika.services.StavkaFaktureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/stavka-fakture")
public class StavkaFaktureController {

    private StavkaFaktureService stavkaFaktureService;

    @Autowired
    public StavkaFaktureController(StavkaFaktureService stavkaFaktureService) {
        this.stavkaFaktureService = stavkaFaktureService;
    }

    @GetMapping(value = "/faktura/{id}")
    public ResponseEntity<List<StavkaFaktureDTO>> getFakturaItems(@PathVariable("id") long id) {
        return new ResponseEntity<>(stavkaFaktureService.getAllStavkaFaktureById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StavkaFaktureDTO>> getItems() {
        return new ResponseEntity<>(stavkaFaktureService.getAllStavkaFakture(), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<StavkaFaktureDTO> saveItem(@RequestBody StavkaFaktureDTO stavkaFaktureDTO) {
        return new ResponseEntity<>(stavkaFaktureService.saveStavkaFakture(stavkaFaktureDTO), HttpStatus.CREATED);
    }

    @PostMapping(value = "/calculate", consumes = "application/json")
    public ResponseEntity<StavkaFaktureDTO> calculate(@RequestBody StavkaFaktureDTO stavkaFaktureDTO) {
        return new ResponseEntity<StavkaFaktureDTO>(stavkaFaktureService.calculate(stavkaFaktureDTO), HttpStatus.OK);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<StavkaFaktureDTO> updateItem(@RequestBody StavkaFaktureDTO stavkaFaktureDTO,
                                                       @PathVariable("id") long id) {
        StavkaFaktureDTO sf = stavkaFaktureService.updateStavkaFakture(stavkaFaktureDTO, id);
        if (sf == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(sf, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (stavkaFaktureService.deleteStavkaFakture(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
