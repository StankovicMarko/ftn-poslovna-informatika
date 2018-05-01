package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.RobaDTO;
import com.example.poslovna_informatika.services.RobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/roba")
public class RobaController {

    private RobaService robaService;

    @Autowired
    public RobaController(RobaService robaService) {
        this.robaService = robaService;
    }


    @GetMapping
    public ResponseEntity<List<RobaDTO>> getItems(Pageable pageable) {
        return new ResponseEntity<>(robaService.getAllItems(pageable), HttpStatus.OK);
    }

    @GetMapping("/preduzece/{id}")
    public ResponseEntity<List<RobaDTO>> getRobaByPreduzeceId(@PathVariable("id") long id) {
        return new ResponseEntity<>(robaService.getAllItemsByPreduzeceId(id), HttpStatus.OK);
    }

    @GetMapping(value = "/grupa-robe/{id}")
    public ResponseEntity<List<RobaDTO>> getItemsGrupaR(@PathVariable("id") long id, Pageable pageable) {
        return new ResponseEntity<>(robaService.getRobaByGrupa(id, pageable), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<RobaDTO> saveItem(@RequestBody RobaDTO robaDTO) {
        return new ResponseEntity<>(robaService.saveRoba(robaDTO), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<RobaDTO> updateItem(@RequestBody RobaDTO robaDTO, @PathVariable("id") long id) {
        RobaDTO r = robaService.updateRoba(robaDTO, id);

        if (r == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (robaService.deleteRoba(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
