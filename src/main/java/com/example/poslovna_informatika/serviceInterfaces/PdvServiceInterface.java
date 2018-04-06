package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.PDV;

import java.util.List;

public interface PdvServiceInterface {

    List<PDV> findAll();

    PDV findByNaziv(String naziv);


    PDV save(PDV pdv);

    void remove(long id);

}
