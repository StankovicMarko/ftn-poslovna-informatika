package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.StavkaCenovnika;
import com.example.poslovna_informatika.model.StavkaFakture;

import java.util.List;

public interface StavkaFaktureServiceInterface {

    List<StavkaFakture> findAll();

    List<StavkaFakture> findAllByFakturaId(long fakturaId);

    List<StavkaFakture> findAllByRobaIn(long robaId);

    List<StavkaFakture> findAllByKolicinaBetween(int pocetnaKolicina, int krajnjaKolicina);

    List<StavkaFakture> findAllByJedinicnaCenaBetween(double pocetnaJedCena, double krajnjaJedCena);

    List<StavkaFakture> findAllByRabatBetween(double pocetniRabat, double krajnjiRabat);

    List<StavkaFakture> findAllByOsnovicaZaPDVBetween(double pocetnaOsnovica, double krajnjaOsnovica);

    List<StavkaFakture> findAllByProcenatPDVBetween(double pocetniProcPdv, double krajnjiProcPdv);

    List<StavkaFakture> findAllByIznosPDVBetween(double pocetniPdv, double krajnjiPdv);

    List<StavkaFakture> findAllByIznosStavkeBetween(double pocetniIznosStavke, double krajnjiIznosStavke);

    StavkaFakture save(StavkaFakture stavkaFakture);

    void remove(long id);

}
