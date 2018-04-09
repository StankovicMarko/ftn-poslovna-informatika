package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.GrupaRobeDTO;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.services.GrupaRobeService;
import com.example.poslovna_informatika.services.PdvService;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/grupa-robe")
public class GrupaRobeController {

    private GrupaRobeService grupaRobeService;
    private PreduzeceService preduzeceService;
    private PdvService pdvService;


    @Autowired
    public GrupaRobeController(GrupaRobeService grupaRobeService, PreduzeceService preduzeceService, PdvService pdvService) {
        this.grupaRobeService = grupaRobeService;
        this.preduzeceService = preduzeceService;
        this.pdvService = pdvService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<GrupaRobeDTO>> getGrupeRobe(@PathVariable("id") long id) {
        List<GrupaRobe> grupaRobes = grupaRobeService.findAllByPreduzeceId(id);
        List<GrupaRobeDTO> grupaRobeDTOS = new ArrayList<GrupaRobeDTO>();
        for (GrupaRobe gr : grupaRobes) {
            grupaRobeDTOS.add(new GrupaRobeDTO(gr));
        }
        return new ResponseEntity<List<GrupaRobeDTO>>(grupaRobeDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<GrupaRobeDTO> saveItem(@RequestBody GrupaRobeDTO grupaRobeDTO) {
        Preduzece preduzece = preduzeceService.findOne(grupaRobeDTO.getPreduzeceId());
        PDV pdv = pdvService.findOne(grupaRobeDTO.getPdvId());

        GrupaRobe gr = new GrupaRobe(grupaRobeDTO.getNaziv(), new ArrayList<Roba>(), preduzece, pdv);

        gr = grupaRobeService.save(gr);
        return new ResponseEntity<GrupaRobeDTO>(new GrupaRobeDTO(gr), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<GrupaRobeDTO> updateItem(@RequestBody GrupaRobeDTO grupaRobeDTO, @PathVariable("id") long id) {
        GrupaRobe gr = grupaRobeService.findOne(id);

        if (gr == null) {
            return new ResponseEntity<GrupaRobeDTO>(HttpStatus.BAD_REQUEST);
        }

        Preduzece preduzece = preduzeceService.findOne(grupaRobeDTO.getPreduzeceId());
        PDV pdv = pdvService.findOne(grupaRobeDTO.getPdvId());


        gr.setNaziv(grupaRobeDTO.getNaziv());
        gr.setPdv(pdv);
        gr.setPreduzece(preduzece);

        gr = grupaRobeService.save(gr);

        return new ResponseEntity<GrupaRobeDTO>(new GrupaRobeDTO(gr), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        GrupaRobe gr = grupaRobeService.findOne(id);
        if (gr != null) {
            grupaRobeService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
