package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.model.Faktura;
import com.example.poslovna_informatika.repositories.FakturaRepository;
import com.example.poslovna_informatika.serviceInterfaces.FakturaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FakturaService implements FakturaServiceInterface {

    private FakturaRepository fakturaRepository;

    @Autowired
    public FakturaService(FakturaRepository fakturaRepository) {
        this.fakturaRepository = fakturaRepository;
    }

    @Override
    public List<Faktura> findAll() {
        return fakturaRepository.findAll();

    }

    @Override
    public Faktura findOne(long id){
        return fakturaRepository.findOne(id);
    }

    @Override
    public Faktura findByBrojFakture(int brojFakture) {
        return fakturaRepository.findByBrojFakture(brojFakture);
    }

    @Override
    public Faktura findByStatus(char[] status) {
        return fakturaRepository.findByStatus(status);
    }

    @Override
    public List<Faktura> findAllByDatumFaktureBetween(Date pocetniDatum, Date krajnjiDatum) {
        return fakturaRepository.findAllByDatumFaktureBetween(pocetniDatum, krajnjiDatum);
    }

    @Override
    public List<Faktura> findAllByDatumValuteBetween(Date pocetniDatum, Date krajnjiDatum) {
        return fakturaRepository.findAllByDatumValuteBetween(pocetniDatum, krajnjiDatum);
    }

    @Override
    public List<Faktura> findAllByOsnovicaBetween(double pocetnaOsnovica, double krajnjaOsnovica) {
        return fakturaRepository.findAllByOsnovicaBetween(pocetnaOsnovica, krajnjaOsnovica);
    }

    @Override
    public List<Faktura> findAllByUkupanPDVBetween(double pocetniUkPdv, double krajnjiUkPdv) {
        return fakturaRepository.findAllByUkupanPDVBetween(pocetniUkPdv, krajnjiUkPdv);
    }

    @Override
    public List<Faktura> findAllByIznosZaPlacanjeBetween(double pocetniIznos, double krajnjiIznos) {
        return fakturaRepository.findAllByIznosZaPlacanjeBetween(pocetniIznos, krajnjiIznos);
    }

    @Override
    public List<Faktura> findAllByPreduzeceId(long preduzeceId) {
        return fakturaRepository.findAllByPreduzeceId(preduzeceId);
    }

    @Override
    public List<Faktura> findAllByPoslovniPartnerId(long poslovniPartnerId) {
        return fakturaRepository.findAllByPoslovniPartnerId(poslovniPartnerId);
    }


    @Override
    public List<Faktura> findAllByPoslovnaGodinaId(long poslovnaGodinaId) {
        return fakturaRepository.findAllByPoslovnaGodinaId(poslovnaGodinaId);
    }

    @Override
    public Faktura save(Faktura faktura) {
        return fakturaRepository.save(faktura);
    }

    @Override
    public void remove(long id) {
         fakturaRepository.delete(id);
    }
}
