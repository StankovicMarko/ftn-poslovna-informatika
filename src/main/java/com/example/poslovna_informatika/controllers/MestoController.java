package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.JedinicaMereDTO;
import com.example.poslovna_informatika.dto.MestoDTO;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.services.JedinicaMereService;
import com.example.poslovna_informatika.services.MestoService;
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
@RequestMapping(value = "api/mesto")
public class MestoController {

    private MestoService mestoService;


    @Autowired
    public MestoController(MestoService mestoService) {
        this.mestoService = mestoService;
    }


    @GetMapping
    public ResponseEntity<List<MestoDTO>> getMesta() {
        List<Mesto> mesta = mestoService.findAll();
        List<MestoDTO> mestoDTOS = new ArrayList<MestoDTO>();
        for (Mesto m : mesta) {
            mestoDTOS.add(new MestoDTO(m));
        }
        return new ResponseEntity<List<MestoDTO>>(mestoDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<MestoDTO> saveItem(@RequestBody MestoDTO mestoDTO) {
        Mesto m = new Mesto(mestoDTO.getGrad(), mestoDTO.getDrzava(), new ArrayList<>(), new ArrayList<>());
        m = mestoService.save(m);
        return new ResponseEntity<MestoDTO>(new MestoDTO(m), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<MestoDTO> updateItem(@RequestBody MestoDTO mestoDTO,
                                                      @PathVariable("id") long id) {
        Mesto m = mestoService.findOne(id);

        if (m == null) {
            return new ResponseEntity<MestoDTO>(HttpStatus.BAD_REQUEST);
        }
        m.setDrzava(mestoDTO.getDrzava());
        m.setGrad(mestoDTO.getGrad());

        m = mestoService.save(m);

        return new ResponseEntity<MestoDTO>(new MestoDTO(m), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        Mesto m = mestoService.findOne(id);
        if (m != null) {
            mestoService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
