package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.PoslovnaGodina;

import java.util.List;

public interface PoslovnaGodinaServiceInterface {

    List<PoslovnaGodina> findAll();

    List<PoslovnaGodina> findAllByGodinaBetween(int pocetnaGodina, int krajnjaGodina);

    List<PoslovnaGodina> findAllByZakljucena(boolean zakljucena);

    PoslovnaGodina save(PoslovnaGodina poslovnaGodina);

    void remove(long id);

}
