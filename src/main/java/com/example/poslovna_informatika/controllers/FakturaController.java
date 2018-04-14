package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.FakturaDTO;
import com.example.poslovna_informatika.model.Faktura;
import com.example.poslovna_informatika.model.PoslovnaGodina;
import com.example.poslovna_informatika.model.PoslovniPartner;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.services.FakturaService;
import com.example.poslovna_informatika.services.PoslovnaGodinaService;
import com.example.poslovna_informatika.services.PoslovniPartnerService;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/faktura")
public class FakturaController {

    private FakturaService fakturaService;
    private PreduzeceService preduzeceService;
    private PoslovnaGodinaService poslovnaGodinaService;
    private PoslovniPartnerService poslovniPartnerService;


    @Autowired
    public FakturaController(FakturaService fakturaService, PreduzeceService preduzeceService,
                             PoslovnaGodinaService poslovnaGodinaService, PoslovniPartnerService poslovniPartnerService) {
        this.fakturaService = fakturaService;
        this.preduzeceService = preduzeceService;
        this.poslovnaGodinaService = poslovnaGodinaService;
        this.poslovniPartnerService = poslovniPartnerService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<FakturaDTO>> getFakture(@PathVariable("id") long id) {
        List<Faktura> fakture = fakturaService.findAllByPreduzeceId(id);
        List<FakturaDTO> fakturaDTOS = new ArrayList<FakturaDTO>();
        for (Faktura f : fakture) {
            fakturaDTOS.add(new FakturaDTO(f));
        }
        return new ResponseEntity<List<FakturaDTO>>(fakturaDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<FakturaDTO> saveItem(@RequestBody FakturaDTO fakturaDTO) {
        Preduzece preduzece = preduzeceService.findOne(fakturaDTO.getPreduzeceId());
        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findOne(fakturaDTO.getPoslovnaGodinaId());
        PoslovniPartner poslovniPartner = poslovniPartnerService.findOne(fakturaDTO.getPoslovniPartnerId());

        Faktura f = new Faktura(fakturaDTO.getDatumFakture(), fakturaDTO.getDatumValute(), fakturaDTO.getOsnovica(),
                fakturaDTO.getUkupanPdv(), fakturaDTO.getIznosZaPlacanje(), fakturaDTO.getStatus(), preduzece, poslovniPartner,
                poslovnaGodina);

        f = fakturaService.save(f);
        return new ResponseEntity<FakturaDTO>(new FakturaDTO(f), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<FakturaDTO> updateItem(@RequestBody FakturaDTO fakturaDTO, @PathVariable("id") long id) {
        Faktura f = fakturaService.findOne(id);

        if (f == null) {
            return new ResponseEntity<FakturaDTO>(HttpStatus.BAD_REQUEST);
        }

        Preduzece preduzece = preduzeceService.findOne(fakturaDTO.getPreduzeceId());
        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findOne(fakturaDTO.getPoslovnaGodinaId());
        PoslovniPartner poslovniPartner = poslovniPartnerService.findOne(fakturaDTO.getPoslovniPartnerId());

        f.setDatumFakture(fakturaDTO.getDatumFakture());
        f.setDatumValute(fakturaDTO.getDatumValute());
        f.setOsnovica(fakturaDTO.getOsnovica());
        f.setUkupanPDV(fakturaDTO.getUkupanPdv());
        f.setIznosZaPlacanje(fakturaDTO.getIznosZaPlacanje());
        f.setStatus(fakturaDTO.getStatus());
        f.setPreduzece(preduzece);
        f.setPoslovniPartner(poslovniPartner);
        f.setPoslovnaGodina(poslovnaGodina);


        f = fakturaService.save(f);

        return new ResponseEntity<FakturaDTO>(new FakturaDTO(f), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        Faktura f = fakturaService.findOne(id);
        if (f != null) {
            fakturaService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
