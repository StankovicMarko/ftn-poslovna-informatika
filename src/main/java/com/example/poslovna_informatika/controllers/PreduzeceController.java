package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PreduzeceDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.services.MestoService;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of controller
 * Required services to be completed
 */
@RestController
@RequestMapping(value = "api/preduzece")
public class PreduzeceController {

    private PreduzeceService preduzeceService;

    private MestoService mestoService;


    @Autowired
    public PreduzeceController(PreduzeceService preduzeceService, MestoService mestoService) {
        this.preduzeceService = preduzeceService;
        this.mestoService = mestoService;
    }


    @GetMapping
    public ResponseEntity<List<PreduzeceDTO>> getPreduzeca() {
        List<Preduzece> preduzeca = preduzeceService.findAll();
        List<PreduzeceDTO> preduzecaDTO = new ArrayList<PreduzeceDTO>();
        for (Preduzece p : preduzeca) {
            preduzecaDTO.add(new PreduzeceDTO(p));
        }
        return new ResponseEntity<List<PreduzeceDTO>>(preduzecaDTO, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PreduzeceDTO> saveItem(@RequestBody PreduzeceDTO preduzeceDTO) {
        Mesto mesto = mestoService.findOne(preduzeceDTO.getMestoId());

        Preduzece p = new Preduzece(preduzeceDTO.getNaziv(), preduzeceDTO.getAdresa(),
                preduzeceDTO.getPib(), preduzeceDTO.getTelefon(), preduzeceDTO.getEmail(),
                preduzeceDTO.getLogoPath(), mesto);

        p = preduzeceService.save(p);
        return new ResponseEntity<PreduzeceDTO>(new PreduzeceDTO(p), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PreduzeceDTO> updateItem(@RequestBody PreduzeceDTO preduzeceDTO, @PathVariable("id") long id) {
        Preduzece p = preduzeceService.findOne(id);

        if (p == null) {
            return new ResponseEntity<PreduzeceDTO>(HttpStatus.BAD_REQUEST);
        }

        Mesto mesto = mestoService.findOne(preduzeceDTO.getMestoId());


        p.setNaziv(preduzeceDTO.getNaziv());
        p.setAdresa(preduzeceDTO.getAdresa());
        p.setPib(preduzeceDTO.getPib());
        p.setTelefon(preduzeceDTO.getTelefon());
        p.setEmail(preduzeceDTO.getEmail());
        p.setLogoPath(preduzeceDTO.getLogoPath());
        p.setMesto(mesto);

        p = preduzeceService.save(p);

        return new ResponseEntity<PreduzeceDTO>(new PreduzeceDTO(p), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        Preduzece p = preduzeceService.findOne(id);
        if (p != null) {
            preduzeceService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
