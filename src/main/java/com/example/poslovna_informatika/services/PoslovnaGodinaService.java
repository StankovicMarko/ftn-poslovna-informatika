package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.PoslovnaGodina;
import com.example.poslovna_informatika.repositories.PdvRepository;
import com.example.poslovna_informatika.repositories.PoslovnaGodinaRepository;
import com.example.poslovna_informatika.serviceInterfaces.PdvServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.PoslovnaGodinaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoslovnaGodinaService implements PoslovnaGodinaServiceInterface {

    private PoslovnaGodinaRepository poslovnaGodinaRepository;

    @Autowired
    public PoslovnaGodinaService(PoslovnaGodinaRepository poslovnaGodinaRepository) {
        this.poslovnaGodinaRepository = poslovnaGodinaRepository;
    }

    @Override
    public List<PoslovnaGodina> findAll() {
        return poslovnaGodinaRepository.findAll();

    }

    @Override
    public PoslovnaGodina findOne(long id){
        return poslovnaGodinaRepository.findOne(id);
    }

    @Override
    public List<PoslovnaGodina> findAllByGodinaBetween(int pocetnaGodina, int krajnjaGodina) {
        return poslovnaGodinaRepository.findAllByGodinaBetween(pocetnaGodina, krajnjaGodina);
    }

    @Override
    public List<PoslovnaGodina> findAllByZakljucena(boolean zakljucena) {
        return poslovnaGodinaRepository.findAllByZakljucena(zakljucena);
    }

    @Override
    public PoslovnaGodina save(PoslovnaGodina poslovnaGodina) {
        return poslovnaGodinaRepository.save(poslovnaGodina);
    }

    @Override
    public void remove(long id) {
        poslovnaGodinaRepository.delete(id);
    }


}
