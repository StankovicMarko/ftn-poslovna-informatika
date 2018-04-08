package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.PoslovniPartner;
import com.example.poslovna_informatika.model.Roba;

import java.util.List;

public interface RobaServiceInterface {

    List<Roba> findAll();

    Roba findOne(long id);

    List<Roba> findAllByNaziv(String nazivRobe);

    List<Roba> findAllByGrupaRobeId(long grupaRobeId);

    List<Roba> findAllByJediniceMereId(long jedinicaMereId);

    Roba save(Roba roba);

    void remove(long id);

}
