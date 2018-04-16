package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.StavkaFaktureDTO;
import com.example.poslovna_informatika.model.Faktura;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaFakture;
import com.example.poslovna_informatika.services.FakturaService;
import com.example.poslovna_informatika.services.RobaService;
import com.example.poslovna_informatika.services.StavkaFaktureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/stavka-fakture")
public class StavkaFaktureController {

    private StavkaFaktureService stavkaFaktureService;
    private FakturaService fakturaService;
    private RobaService robaService;


    @Autowired
    public StavkaFaktureController(StavkaFaktureService stavkaFaktureService,
                                   FakturaService fakturaService, RobaService robaService) {
        this.stavkaFaktureService = stavkaFaktureService;
        this.fakturaService = fakturaService;
        this.robaService = robaService;
    }

    @GetMapping(value="/faktura/{id}")
    public ResponseEntity<List<StavkaFaktureDTO>> getFakturaItems(@PathVariable("id") long id) {
        List<StavkaFakture> stavkaFaktures = stavkaFaktureService.findAllByFakturaId(id);
        List<StavkaFaktureDTO> stavkaFaktureDTOS = new ArrayList<StavkaFaktureDTO>();
        for (StavkaFakture sf : stavkaFaktures) {
            stavkaFaktureDTOS.add(new StavkaFaktureDTO(sf));
        }
        return new ResponseEntity<List<StavkaFaktureDTO>>(stavkaFaktureDTOS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StavkaFaktureDTO>> getItems() {
        List<StavkaFakture> stavkaFaktures = stavkaFaktureService.findAll();
        List<StavkaFaktureDTO> stavkaFaktureDTOS = new ArrayList<StavkaFaktureDTO>();
        for (StavkaFakture sf : stavkaFaktures) {
            stavkaFaktureDTOS.add(new StavkaFaktureDTO(sf));
        }
        return new ResponseEntity<List<StavkaFaktureDTO>>(stavkaFaktureDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<StavkaFaktureDTO> saveItem(@RequestBody StavkaFaktureDTO stavkaFaktureDTO) {

        Faktura f = fakturaService.findOne(stavkaFaktureDTO.getFakturaId());
        Roba r = robaService.findOne(stavkaFaktureDTO.getRobaId());


        StavkaFakture sf = new StavkaFakture(stavkaFaktureDTO.getKolicina(), stavkaFaktureDTO.getJedinicnaCena(),
                stavkaFaktureDTO.getRabat(), stavkaFaktureDTO.getOsnovicaZaPDV(), stavkaFaktureDTO.getProcenatPDV(),
                stavkaFaktureDTO.getIznosPDV(), stavkaFaktureDTO.getIznosStavke(), r, f);

        f.setOsnovica(f.getOsnovica()+stavkaFaktureDTO.getOsnovicaZaPDV());
        f.setUkupanPDV(f.getUkupanPDV()+stavkaFaktureDTO.getIznosPDV());
        f.setIznosZaPlacanje(f.getIznosZaPlacanje() + stavkaFaktureDTO.getIznosStavke());

        f = fakturaService.save(f);

        sf = stavkaFaktureService.save(sf);
        return new ResponseEntity<StavkaFaktureDTO>(new StavkaFaktureDTO(sf), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<StavkaFaktureDTO> updateItem(@RequestBody StavkaFaktureDTO stavkaFaktureDTO,
                                                       @PathVariable("id") long id) {
        StavkaFakture sf = stavkaFaktureService.findOne(id);

        if (sf == null) {
            return new ResponseEntity<StavkaFaktureDTO>(HttpStatus.BAD_REQUEST);
        }

        Faktura f = fakturaService.findOne(stavkaFaktureDTO.getFakturaId());
        Roba r = robaService.findOne(stavkaFaktureDTO.getRobaId());

        sf.setKolicina(stavkaFaktureDTO.getKolicina());
        sf.setJedinicnaCena(stavkaFaktureDTO.getJedinicnaCena());
        sf.setRabat(stavkaFaktureDTO.getRabat());
        sf.setOsnovicaZaPDV(stavkaFaktureDTO.getOsnovicaZaPDV());
        sf.setProcenatPDV(stavkaFaktureDTO.getProcenatPDV());
        sf.setIznosPDV(stavkaFaktureDTO.getIznosPDV());
        sf.setIznosStavke(stavkaFaktureDTO.getIznosStavke());
        sf.setRoba(r);
        sf.setFaktura(f);


        sf = stavkaFaktureService.save(sf);

        return new ResponseEntity<StavkaFaktureDTO>(new StavkaFaktureDTO(sf), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        StavkaFakture sf = stavkaFaktureService.findOne(id);

        Faktura f = fakturaService.findOne(sf.getFaktura().getId());
        f.setOsnovica(f.getOsnovica()-sf.getOsnovicaZaPDV());
        f.setUkupanPDV(f.getUkupanPDV()-sf.getIznosPDV());
        f.setIznosZaPlacanje(f.getIznosZaPlacanje() - sf.getIznosStavke());

        f = fakturaService.save(f);

        if (sf != null) {
            stavkaFaktureService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
