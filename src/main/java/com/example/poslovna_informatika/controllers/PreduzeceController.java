package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.PreduzeceDTO;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping(value = "api/preduzece")
public class PreduzeceController {

    private PreduzeceService preduzeceService;


    @Autowired
    public PreduzeceController(PreduzeceService preduzeceService) {
        this.preduzeceService = preduzeceService;
    }


    @GetMapping(value = "/mesto/{id}")
    public ResponseEntity<List<PreduzeceDTO>> getPreduzecaIzMesta(@PathVariable("id") long id) {
        return new ResponseEntity<>(preduzeceService.getPreduzeceByMesto(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PreduzeceDTO>> getPreduzeca(Principal principal, Pageable pageable) {
        String email = principal.getName();
        if (!email.equals("admin@email.com")) {
            return new ResponseEntity<>(preduzeceService.getPreduzeceByEmail(email), HttpStatus.OK);
        }
        return new ResponseEntity<>(preduzeceService.getAllPreduzeca(pageable), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<PreduzeceDTO> saveItem(@RequestBody PreduzeceDTO preduzeceDTO) {
        return new ResponseEntity<>(preduzeceService.savePreduzece(preduzeceDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<PreduzeceDTO> updateItem(@RequestBody PreduzeceDTO preduzeceDTO, @PathVariable("id") long id) {
        PreduzeceDTO p = preduzeceService.updatePreduzece(preduzeceDTO, id);

        if (p == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(p, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (preduzeceService.deletePreduzece(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
