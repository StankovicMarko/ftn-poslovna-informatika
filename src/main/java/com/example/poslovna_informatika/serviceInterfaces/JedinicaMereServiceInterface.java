package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.JedinicaMere;

import java.util.Date;
import java.util.List;

public interface JedinicaMereServiceInterface {

    List<JedinicaMere> findAll();

    JedinicaMere findOne(long id);

    JedinicaMere findByNaziv(char[] naziv);

    JedinicaMere save(JedinicaMere jedinicaMere);

    void remove(long id);

}
