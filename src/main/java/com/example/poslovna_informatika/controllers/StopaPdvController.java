package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.StopaPdvDTO;
import com.example.poslovna_informatika.services.StopaPdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/stopa-pdv")
public class StopaPdvController {

    private StopaPdvService stopaPdvService;

    @Autowired
    public StopaPdvController(StopaPdvService stopaPdvService) {
        this.stopaPdvService = stopaPdvService;
    }


    @GetMapping
    public ResponseEntity<List<StopaPdvDTO>> getItems() {
        return new ResponseEntity<>(stopaPdvService.getAllStopaPdv(), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<StopaPdvDTO> saveItem(@RequestBody StopaPdvDTO stopaPdvDTO) {
        return new ResponseEntity<>(stopaPdvService.saveStopaPdv(stopaPdvDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<StopaPdvDTO> updateItem(@RequestBody StopaPdvDTO stopaPdvDTO,
                                                  @PathVariable("id") long id) {
        StopaPdvDTO stopa = stopaPdvService.updateStopaPdv(stopaPdvDTO, id);

        if (stopa == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(stopa, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (stopaPdvService.deleteStopaPdv(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
