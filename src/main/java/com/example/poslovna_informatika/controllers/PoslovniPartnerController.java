package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PoslovniPartnerDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.PoslovniPartner;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.services.FakturaService;
import com.example.poslovna_informatika.services.MestoService;
import com.example.poslovna_informatika.services.PoslovniPartnerService;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/poslovni-partner")
public class PoslovniPartnerController {

    private PoslovniPartnerService poslovniPartnerService;
    private PreduzeceService preduzeceService;
    private MestoService mestoService;


    @Autowired
    public PoslovniPartnerController(PoslovniPartnerService poslovniPartnerService, FakturaService fakturaService,
                                     PreduzeceService preduzeceService, MestoService mestoService) {
        this.poslovniPartnerService = poslovniPartnerService;
        this.preduzeceService = preduzeceService;
        this.mestoService = mestoService;
    }

    @GetMapping
    public ResponseEntity<List<PoslovniPartnerDTO>> getAllPartners() {
        List<PoslovniPartner> partners = poslovniPartnerService.findAll();
        List<PoslovniPartnerDTO> partnersDTO = new ArrayList<PoslovniPartnerDTO>();
        for (PoslovniPartner p : partners) {
            partnersDTO.add(new PoslovniPartnerDTO(p));
        }
        return new ResponseEntity<List<PoslovniPartnerDTO>>(partnersDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<PoslovniPartnerDTO> getPartnere(@PathVariable("id") long id) {
        PoslovniPartner poslovniPartner = poslovniPartnerService.findOne(id);
        PoslovniPartnerDTO poslovniPartnerDTO = new PoslovniPartnerDTO(poslovniPartner);

        return new ResponseEntity<PoslovniPartnerDTO>(poslovniPartnerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/preduzece/{id}")
    public ResponseEntity<List<PoslovniPartnerDTO>> getPartnereByPreduzece(@PathVariable("id") long id) {
        List<PoslovniPartner> poslovniPartners = poslovniPartnerService.findAllByPreduzeceId(id);
        List<PoslovniPartnerDTO> poslovniPartnerDTOS = new ArrayList<PoslovniPartnerDTO>();
        for (PoslovniPartner pp : poslovniPartners) {
            poslovniPartnerDTOS.add(new PoslovniPartnerDTO(pp));
        }
        return new ResponseEntity<List<PoslovniPartnerDTO>>(poslovniPartnerDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PoslovniPartnerDTO> saveItem(@RequestBody PoslovniPartnerDTO poslovniPartnerDTO) {
        Preduzece preduzece = preduzeceService.findOne(poslovniPartnerDTO.getPreduzeceId());
        Mesto mesto = mestoService.findOne(poslovniPartnerDTO.getMestoId());

        PoslovniPartner pp = new PoslovniPartner(poslovniPartnerDTO.getNaziv(), poslovniPartnerDTO.getAdresa(),
                poslovniPartnerDTO.getVrsta(), mesto, preduzece);

        pp = poslovniPartnerService.save(pp);
        return new ResponseEntity<PoslovniPartnerDTO>(new PoslovniPartnerDTO(pp), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PoslovniPartnerDTO> updateItem(@RequestBody PoslovniPartnerDTO poslovniPartnerDTO,
                                                         @PathVariable("id") long id) {
        PoslovniPartner pp = poslovniPartnerService.findOne(id);

        if (pp == null) {
            return new ResponseEntity<PoslovniPartnerDTO>(HttpStatus.BAD_REQUEST);
        }

        Preduzece preduzece = preduzeceService.findOne(poslovniPartnerDTO.getPreduzeceId());
        Mesto mesto = mestoService.findOne(poslovniPartnerDTO.getMestoId());

        pp.setNaziv(poslovniPartnerDTO.getNaziv());
        pp.setAdresa(poslovniPartnerDTO.getAdresa());
        pp.setVrsta(poslovniPartnerDTO.getVrsta());
        pp.setMesto(mesto);
        pp.setPreduzece(preduzece);


        pp = poslovniPartnerService.save(pp);

        return new ResponseEntity<PoslovniPartnerDTO>(new PoslovniPartnerDTO(pp), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        PoslovniPartner pp = poslovniPartnerService.findOne(id);
        if (pp != null) {
            poslovniPartnerService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
