package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Preduzece;

import java.util.Date;
import java.util.List;

public interface CenovnikServiceInterface {

    List<Cenovnik> findAll();

    List<Cenovnik> findAllByPreduzeceId(long preduzeceId);

    List<Cenovnik> findAllByDatumVazenjaBetween(Date pocetniDatum, Date kranjiDatum);

    Cenovnik save(Cenovnik cenovnik);

    void remove(long id);

}
