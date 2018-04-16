package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.GrupaRobeDTO;
import com.example.poslovna_informatika.dto.StavkaCenovnikaDTO;
import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaCenovnika;
import com.example.poslovna_informatika.services.CenovnikService;
import com.example.poslovna_informatika.services.RobaService;
import com.example.poslovna_informatika.services.StavkaCenovnikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/stavka-cenovnika")
public class StavkaCenovnikaController {

    private StavkaCenovnikaService stavkaCenovnikaService;
    private CenovnikService cenovnikService;
    private RobaService robaService;


    @Autowired
    public StavkaCenovnikaController(StavkaCenovnikaService stavkaCenovnikaService,
                                     CenovnikService cenovnikService, RobaService robaService) {
        this.stavkaCenovnikaService = stavkaCenovnikaService;
        this.cenovnikService = cenovnikService;
        this.robaService = robaService;
    }

//    @GetMapping(value = "/preduzece/{pred-id}/cenovnik/{cen-id}")
//    public ResponseEntity<List<StavkaCenovnikaDTO>>  getItemsByCenovnik(@PathVariable("pred-id") long predId,
//                                                                       @PathVariable("cen-id") long cenId) {
//        List<StavkaCenovnika> stavkaCenovnikasPred = stavkaCenovnikaService.findAllByCenovnikId(predId);
//        List<StavkaCenovnika> stavkaCenovnikasCen = stavkaCenovnikaService.findAllByCenovnikId(cenId);
//        stavkaCenovnikasPred.retainAll(stavkaCenovnikasCen);
//
//        List<StavkaCenovnikaDTO> stavkaCenovnikaDTOS = new ArrayList<StavkaCenovnikaDTO>();
//        for (StavkaCenovnika sc :  stavkaCenovnikasPred) {
//            stavkaCenovnikaDTOS.add(new StavkaCenovnikaDTO(sc));
//        }
//        return new ResponseEntity<List<StavkaCenovnikaDTO>>(stavkaCenovnikaDTOS, HttpStatus.OK);
//    }

    @GetMapping(value="/cenovnik/{id}")
    public ResponseEntity<List<StavkaCenovnikaDTO>> getItemsByCenovnik(@PathVariable("id") long id) {
        List<StavkaCenovnika> stavkaCenovnikas = stavkaCenovnikaService.findAllByCenovnikId(id);
        List<StavkaCenovnikaDTO> stavkaCenovnikaDTOS = new ArrayList<StavkaCenovnikaDTO>();
        for (StavkaCenovnika sc : stavkaCenovnikas) {
            stavkaCenovnikaDTOS.add(new StavkaCenovnikaDTO(sc));
        }
        return new ResponseEntity<List<StavkaCenovnikaDTO>>(stavkaCenovnikaDTOS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<StavkaCenovnikaDTO>> getItems() {
        List<StavkaCenovnika> stavkaCenovnikas = stavkaCenovnikaService.findAll();
        List<StavkaCenovnikaDTO> stavkaCenovnikaDTOS = new ArrayList<StavkaCenovnikaDTO>();
        for (StavkaCenovnika sc : stavkaCenovnikas) {
            stavkaCenovnikaDTOS.add(new StavkaCenovnikaDTO(sc));
        }
        return new ResponseEntity<List<StavkaCenovnikaDTO>>(stavkaCenovnikaDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<StavkaCenovnikaDTO> saveItem(@RequestBody StavkaCenovnikaDTO stavkaCenovnikaDTO) {

        Cenovnik c = cenovnikService.findOne(stavkaCenovnikaDTO.getCenovnikId());
        Roba r = robaService.findOne(stavkaCenovnikaDTO.getRobaId());


        StavkaCenovnika sc = new StavkaCenovnika(stavkaCenovnikaDTO.getCena(), r, c);

        sc = stavkaCenovnikaService.save(sc);
        return new ResponseEntity<StavkaCenovnikaDTO>(new StavkaCenovnikaDTO(sc), HttpStatus.CREATED);

    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<StavkaCenovnikaDTO> updateItem(@RequestBody StavkaCenovnikaDTO stavkaCenovnikaDTO,
                                                         @PathVariable("id") long id) {
        StavkaCenovnika sc = stavkaCenovnikaService.findOne(id);

        if (sc == null) {
            return new ResponseEntity<StavkaCenovnikaDTO>(HttpStatus.BAD_REQUEST);
        }

        Cenovnik c = cenovnikService.findOne(stavkaCenovnikaDTO.getCenovnikId());
        Roba r = robaService.findOne(stavkaCenovnikaDTO.getRobaId());


        sc.setCena(stavkaCenovnikaDTO.getCena());
        sc.setCenovnik(c);
        sc.setRoba(r);

        sc = stavkaCenovnikaService.save(sc);

        return new ResponseEntity<StavkaCenovnikaDTO>(new StavkaCenovnikaDTO(sc), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        StavkaCenovnika sc = stavkaCenovnikaService.findOne(id);
        if (sc != null) {
            stavkaCenovnikaService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
