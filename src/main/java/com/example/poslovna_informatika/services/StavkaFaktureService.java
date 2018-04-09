package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.StavkaCenovnika;
import com.example.poslovna_informatika.model.StavkaFakture;
import com.example.poslovna_informatika.repositories.StavkaCenovnikaRepository;
import com.example.poslovna_informatika.repositories.StavkaFaktureRepository;
import com.example.poslovna_informatika.serviceInterfaces.StavkaCenovnikaServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.StavkaFaktureServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StavkaFaktureService implements StavkaFaktureServiceInterface {

    private StavkaFaktureRepository stavkaFaktureRepository;

    @Autowired
    public StavkaFaktureService(StavkaFaktureRepository stavkaCenovnikaRepository) {
        this.stavkaFaktureRepository = stavkaCenovnikaRepository;
    }

    @Override
    public List<StavkaFakture> findAll() {
        return stavkaFaktureRepository.findAll();

    }

    @Override
    public StavkaFakture findOne(long id){
        return stavkaFaktureRepository.findOne(id);
    }

    @Override
    public List<StavkaFakture> findAllByFakturaId(long fakturaId){
        return stavkaFaktureRepository.findAllByFakturaId(fakturaId);
    }

    @Override
    public List<StavkaFakture> findAllByRobaIn(long robaId){
        return stavkaFaktureRepository.findAllByRobaIn(robaId);
    }

    @Override
    public List<StavkaFakture> findAllByKolicinaBetween(int pocetnaKolicina, int krajnjaKolicina){
        return stavkaFaktureRepository.findAllByKolicinaBetween(pocetnaKolicina, krajnjaKolicina);
    }

    @Override
    public List<StavkaFakture> findAllByJedinicnaCenaBetween(double pocetnaJedCena, double krajnjaJedCena){
        return stavkaFaktureRepository.findAllByJedinicnaCenaBetween(pocetnaJedCena, krajnjaJedCena);
    }

    @Override
    public List<StavkaFakture> findAllByRabatBetween(double pocetniRabat, double krajnjiRabat){
        return stavkaFaktureRepository.findAllByRabatBetween(pocetniRabat, krajnjiRabat);
    }

    @Override
    public List<StavkaFakture> findAllByOsnovicaZaPDVBetween(double pocetnaOsnovica, double krajnjaOsnovica){
        return stavkaFaktureRepository.findAllByOsnovicaZaPDVBetween(pocetnaOsnovica, krajnjaOsnovica);
    }

    @Override
    public List<StavkaFakture> findAllByProcenatPDVBetween(double pocetniProcPdv, double krajnjiProcPdv){
        return stavkaFaktureRepository.findAllByProcenatPDVBetween(pocetniProcPdv, krajnjiProcPdv);
    }

    @Override
    public List<StavkaFakture> findAllByIznosPDVBetween(double pocetniPdv, double krajnjiPdv){
        return stavkaFaktureRepository.findAllByIznosPDVBetween(pocetniPdv,krajnjiPdv);
    }

    @Override
    public List<StavkaFakture> findAllByIznosStavkeBetween(double pocetniIznosStavke, double krajnjiIznosStavke){
        return stavkaFaktureRepository.findAllByIznosStavkeBetween(pocetniIznosStavke,krajnjiIznosStavke);
    }

    @Override
    public StavkaFakture save(StavkaFakture stavkaFakture) {
        return stavkaFaktureRepository.save(stavkaFakture);
    }

    @Override
    public void remove(long id) {
        stavkaFaktureRepository.delete(id);
    }


}
