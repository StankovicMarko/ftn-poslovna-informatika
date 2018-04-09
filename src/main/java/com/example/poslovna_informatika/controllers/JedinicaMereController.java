package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.JedinicaMereDTO;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.services.JedinicaMereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/jedinica-mere")
public class JedinicaMereController {

    private JedinicaMereService jedinicaMereService;


    @Autowired
    public JedinicaMereController(JedinicaMereService jedinicaMereService) {
        this.jedinicaMereService = jedinicaMereService;
    }


    @GetMapping
    public ResponseEntity<List<JedinicaMereDTO>> getJediniceMere() {
        List<JedinicaMere> jediniceMera = jedinicaMereService.findAll();
        List<JedinicaMereDTO> jedinicaMereDTOS = new ArrayList<JedinicaMereDTO>();
        for (JedinicaMere jm : jediniceMera) {
            jedinicaMereDTOS.add(new JedinicaMereDTO(jm));
        }
        return new ResponseEntity<List<JedinicaMereDTO>>(jedinicaMereDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<JedinicaMere> saveItem(@RequestBody JedinicaMere jedinicaMere) {
        JedinicaMere jm = new JedinicaMere(jedinicaMere.getNaziv());
        jedinicaMereService.save(jm);
        return new ResponseEntity<JedinicaMere>(jm, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<JedinicaMereDTO> updateItem(@RequestBody JedinicaMereDTO jedinicaMereDTO,
                                                      @PathVariable("id") long id) {
        JedinicaMere jm = jedinicaMereService.findOne(id);

        if (jm == null) {
            return new ResponseEntity<JedinicaMereDTO>(HttpStatus.BAD_REQUEST);
        }

        jm.setNaziv(jedinicaMereDTO.getNaziv());

        jm = jedinicaMereService.save(jm);

        return new ResponseEntity<JedinicaMereDTO>(new JedinicaMereDTO(jm), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        JedinicaMere jm = jedinicaMereService.findOne(id);
        if (jm != null) {
            jedinicaMereService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
