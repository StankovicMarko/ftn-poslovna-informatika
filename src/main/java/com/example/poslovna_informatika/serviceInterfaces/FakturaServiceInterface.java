package com.example.poslovna_informatika.serviceInterfaces;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Faktura;

import java.util.Date;
import java.util.List;

public interface FakturaServiceInterface {

    List<Faktura> findAll();

    Faktura findByBrojFakture(int brojFakture);

    Faktura findByStatus(char[] status);

    List<Faktura> findAllByDatumFaktureBetween(Date pocetniDatum, Date krajnjiDatum);

    List<Faktura> findAllByDatumValuteBetween(Date pocetniDatum, Date krajnjiDatum);

    List<Faktura> findAllByOsnovicaBetween(double pocetnaOsnovica, double krajnjaOsnovica);

    List<Faktura> findAllByUkupanPDVBetween(double pocetniUkPdv, double krajnjiUkPdv);

    List<Faktura> findAllByIznosZaPlacanjeBetween(double pocetniIznos, double krajnjiIznos);

    List<Faktura> findAllByPreduzeceId(long preduzeceId);

    List<Faktura> findAllByPoslovniPartnerId(long poslovniPartnerId);

    List<Faktura> findAllByPoslovnaGodinaId(long poslovnaGodinaId);

    Faktura save(Faktura faktura);

    void remove(long id);

}
