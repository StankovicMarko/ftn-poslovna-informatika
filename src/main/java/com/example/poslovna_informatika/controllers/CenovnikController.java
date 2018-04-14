package com.example.poslovna_informatika.controllers;

import com.example.poslovna_informatika.dto.CenovnikDTO;
import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.services.CenovnikService;
import com.example.poslovna_informatika.services.PreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/cenovnik")
public class CenovnikController {

    private CenovnikService cenovnikService;
    private PreduzeceService preduzeceService;


    @Autowired
    public CenovnikController(CenovnikService cenovnikService, PreduzeceService preduzeceService) {
        this.cenovnikService = cenovnikService;
        this.preduzeceService = preduzeceService;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<List<CenovnikDTO>> getCenovnici(@PathVariable("id") long id) {
        List<Cenovnik> cenovnici = cenovnikService.findAllByPreduzeceId(id);
        List<CenovnikDTO> cenovniciDTOS = new ArrayList<CenovnikDTO>();
        for (Cenovnik c : cenovnici) {
            cenovniciDTOS.add(new CenovnikDTO(c));
        }
        return new ResponseEntity<List<CenovnikDTO>>(cenovniciDTOS, HttpStatus.OK);
    }


    @PostMapping(consumes = "application/json")
    public ResponseEntity<CenovnikDTO> saveItem(@RequestBody CenovnikDTO cenovnikDTO) {

        Preduzece preduzece = preduzeceService.findOne(cenovnikDTO.getPreduzeceId());
        Cenovnik c = new Cenovnik(cenovnikDTO.getDatumVazenja(), preduzece);



        c = cenovnikService.save(c);

        return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c), HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<CenovnikDTO> updateItem(@RequestBody CenovnikDTO cenovnikDTO, @PathVariable("id") long id) {
        Cenovnik c = cenovnikService.findOne(id);

        if (c == null) {
            return new ResponseEntity<CenovnikDTO>(HttpStatus.BAD_REQUEST);
        }

        Preduzece p = preduzeceService.findOne(cenovnikDTO.getPreduzeceId());


        c.setDatumVazenja(cenovnikDTO.getDatumVazenja());
        c.setPreduzece(p);

        c = cenovnikService.save(c);

        return new ResponseEntity<CenovnikDTO>(new CenovnikDTO(c), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        Cenovnik c = cenovnikService.findOne(id);
        if (c != null) {
            cenovnikService.remove(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
