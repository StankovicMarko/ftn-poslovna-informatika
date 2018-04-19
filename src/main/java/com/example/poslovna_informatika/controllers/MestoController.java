package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.MestoDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.services.MestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/mesto")
public class MestoController {

    private MestoService mestoService;


    @Autowired
    public MestoController(MestoService mestoService) {
        this.mestoService = mestoService;
    }


    @GetMapping
    public ResponseEntity<List<MestoDTO>> getMesta() {
        return new ResponseEntity<>(mestoService.getAllMesta(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Mesto> saveItem(@RequestBody Mesto mesto) {
        return new ResponseEntity<>(mestoService.save(mesto), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<MestoDTO> updateItem(@RequestBody MestoDTO mestoDTO,
                                               @PathVariable("id") long id) {
        MestoDTO m = mestoService.updateMesto(mestoDTO, id);

        if (m == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(m, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (mestoService.deleteMesto(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
