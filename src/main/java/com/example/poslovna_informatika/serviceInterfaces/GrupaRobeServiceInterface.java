package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Faktura;
import com.example.poslovna_informatika.model.GrupaRobe;

import java.util.Date;
import java.util.List;

public interface GrupaRobeServiceInterface {

    List<GrupaRobe> findAll();

    GrupaRobe findByNaziv(String naziv);

    List<GrupaRobe> findAllByPreduzeceId(long preduzeceId);

    List<GrupaRobe> findAllByPdvId(long pdvId);

    GrupaRobe save(GrupaRobe grupaRobe);

    void remove(long id);

}
