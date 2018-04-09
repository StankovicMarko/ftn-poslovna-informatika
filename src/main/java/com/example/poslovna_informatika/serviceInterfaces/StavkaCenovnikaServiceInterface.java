package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaCenovnika;

import java.util.List;

public interface StavkaCenovnikaServiceInterface {

    List<StavkaCenovnika> findAll();

    StavkaCenovnika findOne(long id);

    List<StavkaCenovnika> findAllByCenovnikId(long cenovnikId);

    List<StavkaCenovnika> findAllByRobaId(long robaId);

    List<StavkaCenovnika> findAllByCenaBetween(double pocetnaCena, double krajnjaCena);

    StavkaCenovnika save(StavkaCenovnika stavkaCenovnika);

    void remove(long id);

}
