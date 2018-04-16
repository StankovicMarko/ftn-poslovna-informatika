package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.RobaDTO;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.services.GrupaRobeService;
import com.example.poslovna_informatika.services.JedinicaMereService;
import com.example.poslovna_informatika.services.RobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/roba")
public class RobaController {

    private RobaService robaService;
    private JedinicaMereService jedinicaMereService;
    private GrupaRobeService grupaRobeService;

    @Autowired
    public RobaController(RobaService robaService, JedinicaMereService jedinicaMereService,
                          GrupaRobeService grupaRobeService) {
        this.robaService = robaService;
        this.jedinicaMereService = jedinicaMereService;
        this.grupaRobeService = grupaRobeService;
    }


    @GetMapping
    public ResponseEntity<List<RobaDTO>> getItems() {
        List<Roba> robas = robaService.findAll();
        List<RobaDTO> robaDTOS = new ArrayList<RobaDTO>();
        for (Roba r : robas) {
            robaDTOS.add(new RobaDTO(r));
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/grupa-robe/{id}")
    public ResponseEntity<List<RobaDTO>> getItemsGrupaR(@PathVariable("id") long id) {
        List<Roba> robas = robaService.findAllByGrupaRobeId(id);
        List<RobaDTO> robaDTOS = new ArrayList<RobaDTO>();
        for (Roba r : robas) {
            robaDTOS.add(new RobaDTO(r));
        }
        return new ResponseEntity<List<RobaDTO>>(robaDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<RobaDTO> saveItem(@RequestBody RobaDTO robaDTO) {
        JedinicaMere jm = jedinicaMereService.findOne(robaDTO.getJedinicaMereId());
        GrupaRobe gr = grupaRobeService.findOne(robaDTO.getGrupaRobeId());


        Roba r = new Roba(robaDTO.getNaziv(), jm, gr);

        r = robaService.save(r);
        return new ResponseEntity<RobaDTO>(new RobaDTO(r), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<RobaDTO> updateItem(@RequestBody RobaDTO robaDTO, @PathVariable("id") long id) {
        Roba r = robaService.findOne(id);

        if (r == null) {
            return new ResponseEntity<RobaDTO>(HttpStatus.BAD_REQUEST);
        }

        JedinicaMere jm = jedinicaMereService.findOne(robaDTO.getJedinicaMereId());
        GrupaRobe gr = grupaRobeService.findOne(robaDTO.getGrupaRobeId());


        r.setNaziv(robaDTO.getNaziv());
        r.setJedinicaMere(jm);
        r.setGrupaRobe(gr);

        r = robaService.save(r);

        return new ResponseEntity<RobaDTO>(new RobaDTO(r), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        Roba r = robaService.findOne(id);
        if (r != null) {
            robaService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
