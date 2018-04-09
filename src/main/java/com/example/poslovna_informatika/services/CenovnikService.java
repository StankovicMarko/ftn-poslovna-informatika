package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.CenovnikRepository;
import com.example.poslovna_informatika.repositories.PreduzeceRepository;
import com.example.poslovna_informatika.serviceInterfaces.CenovnikServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.PreduzeceServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CenovnikService implements CenovnikServiceInterface {

    private CenovnikRepository cenovnikRepository;

    @Autowired
    public CenovnikService(CenovnikRepository cenovnikRepository) {
        this.cenovnikRepository = cenovnikRepository;
    }

    @Override
    public List<Cenovnik> findAll() {
        return cenovnikRepository.findAll();
    }

    @Override
    public Cenovnik findOne(long id){
        return cenovnikRepository.findOne(id);
    }

    @Override
    public List<Cenovnik> findAllByPreduzeceId(long preduzeceId) {
        return cenovnikRepository.findAllByPreduzeceId(preduzeceId);
    }

    @Override
    public List<Cenovnik> findAllByDatumVazenjaBetween(Date pocetniDatum, Date kranjiDatum) {
        return cenovnikRepository.findAllByDatumVazenjaBetween(pocetniDatum, kranjiDatum);
    }

    @Override
    public Cenovnik save(Cenovnik cenovnik) {
        return cenovnikRepository.save(cenovnik);
    }

    @Override
    public void remove(long id) {
        cenovnikRepository.delete(id);
    }
}
