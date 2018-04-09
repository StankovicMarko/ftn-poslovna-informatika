package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.PDV;

import java.util.List;

public interface PdvServiceInterface {

    List<PDV> findAll();

    PDV findOne(long id);

    PDV findByNaziv(String naziv);

    PDV save(PDV pdv);

    void remove(long id);

}
