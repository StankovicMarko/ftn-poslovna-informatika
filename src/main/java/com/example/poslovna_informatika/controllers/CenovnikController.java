package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.CenovnikDTO;
import com.example.poslovna_informatika.services.CenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/cenovnik")
public class CenovnikController {

    private CenovnikService cenovnikService;

    @Autowired
    public CenovnikController(CenovnikService cenovnikService) {
        this.cenovnikService = cenovnikService;
    }

    @GetMapping
    public ResponseEntity<List<CenovnikDTO>> getCenovnici(Pageable pageable) {
        return new ResponseEntity<>(cenovnikService.getCenovniciDTO(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<CenovnikDTO>> getCenovnici(@PathVariable("id") long id, Pageable pageable) {
        return new ResponseEntity<>(cenovnikService.getCenovniciDTObyId(id, pageable), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<CenovnikDTO> saveItem(@RequestBody CenovnikDTO cenovnikDTO) {
        return new ResponseEntity<>(cenovnikService.saveCenovnikDTO(cenovnikDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<CenovnikDTO> updateItem(@RequestBody CenovnikDTO cenovnikDTO, @PathVariable("id") long id) {
        CenovnikDTO c = cenovnikService.updateCenovnik(cenovnikDTO, id);

        if (c == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(c, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (cenovnikService.deleteCenovnik(id)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
