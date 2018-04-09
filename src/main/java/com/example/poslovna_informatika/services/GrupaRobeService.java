package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Faktura;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.repositories.FakturaRepository;
import com.example.poslovna_informatika.repositories.GrupaRobeRepository;
import com.example.poslovna_informatika.serviceInterfaces.FakturaServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.GrupaRobeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GrupaRobeService implements GrupaRobeServiceInterface {

    private GrupaRobeRepository grupaRobeRepository;

    @Autowired
    public GrupaRobeService(GrupaRobeRepository grupaRobeRepository) {
        this.grupaRobeRepository = grupaRobeRepository;
    }

    @Override
    public List<GrupaRobe> findAll() {
        return grupaRobeRepository.findAll();

    }

    @Override
    public GrupaRobe findOne(long id){
        return grupaRobeRepository.findOne(id);
    }

    @Override
    public GrupaRobe findByNaziv(String naziv) {
        return grupaRobeRepository.findByNaziv(naziv);
    }

    @Override
    public List<GrupaRobe> findAllByPreduzeceId(long preduzeceId) {
        return grupaRobeRepository.findAllByPreduzeceId(preduzeceId);
    }

    @Override
    public List<GrupaRobe> findAllByPdvId(long pdvId) {
        return grupaRobeRepository.findAllByPdvId(pdvId);
    }

    @Override
    public GrupaRobe save(GrupaRobe grupaRobe) {
        return grupaRobeRepository.save(grupaRobe);
    }

    @Override
    public void remove(long id) {
        grupaRobeRepository.delete(id);
    }
}
