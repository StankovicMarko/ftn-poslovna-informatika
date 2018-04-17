package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.JedinicaMereDTO;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.services.JedinicaMereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(jedinicaMereService.getAllJedinicaMere(), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<JedinicaMere> saveItem(@RequestBody JedinicaMere jedinicaMere) {
        return new ResponseEntity<>(jedinicaMereService.saveJedinicaMere(jedinicaMere), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<JedinicaMereDTO> updateItem(@RequestBody JedinicaMereDTO jedinicaMereDTO,
                                                      @PathVariable("id") long id) {
        JedinicaMereDTO jm = jedinicaMereService.updateJedinicaMere(jedinicaMereDTO, id);
        if (jm == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(jm, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (jedinicaMereService.deleteJedinicaMere(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
