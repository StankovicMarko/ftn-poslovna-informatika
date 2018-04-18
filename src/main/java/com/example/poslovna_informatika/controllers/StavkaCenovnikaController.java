package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.StavkaCenovnikaDTO;
import com.example.poslovna_informatika.services.StavkaCenovnikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/stavka-cenovnika")
public class StavkaCenovnikaController {

    private StavkaCenovnikaService stavkaCenovnikaService;

    @Autowired
    public StavkaCenovnikaController(StavkaCenovnikaService stavkaCenovnikaService) {
        this.stavkaCenovnikaService = stavkaCenovnikaService;
    }


    @GetMapping(value = "/cenovnik/{id}")
    public ResponseEntity<List<StavkaCenovnikaDTO>> getItemsByCenovnik(@PathVariable("id") long id) {
        return new ResponseEntity<>(stavkaCenovnikaService.getStavkeCenById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StavkaCenovnikaDTO>> getItems() {
        return new ResponseEntity<>(stavkaCenovnikaService.getAllStavkeCen(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<StavkaCenovnikaDTO> saveItem(@RequestBody StavkaCenovnikaDTO stavkaCenovnikaDTO) {
        return new ResponseEntity<>(stavkaCenovnikaService.saveCenovnik(stavkaCenovnikaDTO), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<StavkaCenovnikaDTO> updateItem(@RequestBody StavkaCenovnikaDTO stavkaCenovnikaDTO,
                                                         @PathVariable("id") long id) {
        StavkaCenovnikaDTO sc = stavkaCenovnikaService.updateStavkaCen(stavkaCenovnikaDTO, id);

        if (sc == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(sc, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (stavkaCenovnikaService.deleteStavkaCen(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
