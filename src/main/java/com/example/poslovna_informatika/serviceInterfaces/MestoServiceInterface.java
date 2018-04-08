package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.model.Mesto;

import java.util.List;

public interface MestoServiceInterface {

    List<Mesto> findAll();

    Mesto findOne(long mestoId);

    Mesto findByGrad(String grad);

    List<Mesto> findByDrzava(String drzava);

    Mesto save(Mesto cenovnik);

    void remove(long id);

}
