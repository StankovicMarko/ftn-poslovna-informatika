package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PoslovniPartnerDTO;
import com.example.poslovna_informatika.services.PoslovniPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/poslovni-partner")
public class PoslovniPartnerController {

    private PoslovniPartnerService poslovniPartnerService;


    @Autowired
    public PoslovniPartnerController(PoslovniPartnerService poslovniPartnerService) {
        this.poslovniPartnerService = poslovniPartnerService;
    }

    @GetMapping
    public ResponseEntity<List<PoslovniPartnerDTO>> getAllPartners() {
        return new ResponseEntity<>(poslovniPartnerService.getAllPartners(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PoslovniPartnerDTO> getPartnere(@PathVariable("id") long id) {
        return new ResponseEntity<>(poslovniPartnerService.getPartnerById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/preduzece/{id}")
    public ResponseEntity<List<PoslovniPartnerDTO>> getPartnereByPreduzece(@PathVariable("id") long id) {
        return new ResponseEntity<>(poslovniPartnerService.getPartnersByPreduzece(id), HttpStatus.OK);
    }

    @GetMapping(value = "/preduzece/{preduzeceId}/mesto/{mestoId}")
    public ResponseEntity<List<PoslovniPartnerDTO>> getPartnereByPreduzeceAndMesto(@PathVariable("preduzeceId") long preduzeceId,
                                                                                   @PathVariable("mestoId") long mestoId) {
        return new ResponseEntity<>(poslovniPartnerService
                .getPartnerByPreduzeceAndMesto(preduzeceId, mestoId), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<PoslovniPartnerDTO> saveItem(@RequestBody PoslovniPartnerDTO poslovniPartnerDTO) {
        return new ResponseEntity<>(poslovniPartnerService.savePoslovniPartner(poslovniPartnerDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PoslovniPartnerDTO> updateItem(@RequestBody PoslovniPartnerDTO poslovniPartnerDTO,
                                                         @PathVariable("id") long id) {
        PoslovniPartnerDTO pp = poslovniPartnerService.updatePoslovniPartner(poslovniPartnerDTO, id);

        if (pp == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(pp, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (poslovniPartnerService.deletePoslovniPartner(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
