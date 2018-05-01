package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.GrupaRobeDTO;
import com.example.poslovna_informatika.services.GrupaRobeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/grupa-robe")
public class GrupaRobeController {

    private GrupaRobeService grupaRobeService;


    @Autowired
    public GrupaRobeController(GrupaRobeService grupaRobeService) {
        this.grupaRobeService = grupaRobeService;
    }


    @GetMapping(value = "/preduzece/{pred-id}/pdv/{pdv-id}")
    public ResponseEntity<List<GrupaRobeDTO>> getGrupeRobeByPredAndPdv(@PathVariable("pred-id") long predId,
                                                                       @PathVariable("pdv-id") long pdvId,
                                                                       Pageable pageable) {
        return new ResponseEntity<>(grupaRobeService.getGrupaRobeByPredAndPdv(predId, pdvId, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/preduzece/{pred-id}")
    public ResponseEntity<List<GrupaRobeDTO>> getGrupeRobeByPred(@PathVariable("pred-id") long predId, Pageable pageable) {
        return new ResponseEntity<>(grupaRobeService.getGrupaRobeByPred(predId, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<GrupaRobeDTO>> getGrupeRobeById(@PathVariable("id") long id, Pageable pageable) {
        return new ResponseEntity<>(grupaRobeService.getAllGrupaRobeById(id, pageable), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<GrupaRobeDTO>> getGrupeRobeAll(Pageable pageable) {
        return new ResponseEntity<>(grupaRobeService.getAllGrupaRobe(pageable), HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<GrupaRobeDTO> saveItem(@RequestBody GrupaRobeDTO grupaRobeDTO) {
        return new ResponseEntity<>(grupaRobeService.saveGrupaRobeDto(grupaRobeDTO), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<GrupaRobeDTO> updateItem(@RequestBody GrupaRobeDTO grupaRobeDTO, @PathVariable("id") long id) {
        GrupaRobeDTO gr = grupaRobeService.updateGrupaRobe(grupaRobeDTO, id);
        if (gr == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(gr, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (grupaRobeService.deleteGrupaRobe(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
