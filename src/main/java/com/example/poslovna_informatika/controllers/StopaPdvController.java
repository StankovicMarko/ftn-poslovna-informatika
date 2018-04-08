package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.MestoDTO;
import com.example.poslovna_informatika.dto.StopaPdvDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.StopaPDV;
import com.example.poslovna_informatika.services.MestoService;
import com.example.poslovna_informatika.services.PdvService;
import com.example.poslovna_informatika.services.StopaPdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Example of controller
 * Required services to be completed
 */
@RestController
@RequestMapping(value = "api/mesto")
public class StopaPdvController {

    private StopaPdvService stopaPdvService;
    private PdvService pdvService;


    @Autowired
    public StopaPdvController(StopaPdvService stopaPdvService, PdvService pdvService) {
        this.stopaPdvService = stopaPdvService;
        this.pdvService = pdvService;
    }




    @GetMapping(value="/{pdv-id}")
    public ResponseEntity<List<StopaPdvDTO>> getItems() {
        List<StopaPDV> stopaPDVS = stopaPdvService.findAll();
        List<StopaPdvDTO> stopaPdvDTOS= new ArrayList<StopaPdvDTO>();
        for (StopaPDV st : stopaPDVS) {
            stopaPdvDTOS.add(new StopaPdvDTO(st));
        }
        return new ResponseEntity<List<StopaPdvDTO>>(stopaPdvDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<StopaPdvDTO> saveItem(@RequestBody StopaPdvDTO stopaPdvDTO) {
        PDV pdv = pdvService.findOne(stopaPdvDTO.getPdvId());

        StopaPDV stopa = new StopaPDV(stopaPdvDTO.getProcenat(), stopaPdvDTO.getDatumVazenja(), pdv);
        stopa = stopaPdvService.save(stopa);
        return new ResponseEntity<StopaPdvDTO>(new StopaPdvDTO(stopa), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<StopaPdvDTO> updateItem(@RequestBody StopaPdvDTO stopaPdvDTO,
                                                      @PathVariable("id") long id) {
        StopaPDV stopa = stopaPdvService.findOne(id);

        if (stopa == null) {
            return new ResponseEntity<StopaPdvDTO>(HttpStatus.BAD_REQUEST);
        }
        PDV pdv = pdvService.findOne(stopaPdvDTO.getPdvId());


        stopa.setDatumVazenja(stopaPdvDTO.getDatumVazenja());
        stopa.setProcenat(stopaPdvDTO.getProcenat());
        stopa.setPdv(pdv);


        stopa = stopaPdvService.save(stopa);

        return new ResponseEntity<StopaPdvDTO>(new StopaPdvDTO(stopa), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        StopaPDV stopa = stopaPdvService.findOne(id);
        if (stopa != null) {
            stopaPdvService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
