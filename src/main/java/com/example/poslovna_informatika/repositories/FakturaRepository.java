package com.example.poslovna_informatika.repositories;

import com.example.poslovna_informatika.model.Faktura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FakturaRepository extends JpaRepository<Faktura, Long> {

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

}
