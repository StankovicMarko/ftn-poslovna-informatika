package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.PoslovniPartner;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.repositories.PoslovniPartnerRepository;
import com.example.poslovna_informatika.repositories.RobaRepository;
import com.example.poslovna_informatika.serviceInterfaces.PoslovniPartnerServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.RobaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RobaService implements RobaServiceInterface {

    private RobaRepository robaRepository;

    @Autowired
    public RobaService(RobaRepository robaRepository) {
        this.robaRepository = robaRepository;
    }

    @Override
    public List<Roba> findAll() {
        return robaRepository.findAll();

    }

    @Override
    public Roba findOne(long id){
        return robaRepository.findOne(id);
    }

    @Override
    public List<Roba> findAllByNaziv(String nazivRobe) {
        return robaRepository.findAllByNaziv(nazivRobe);
    }

    @Override
    public List<Roba> findAllByGrupaRobeId(long grupaRobeId) {
        return robaRepository.findAllByGrupaRobeId(grupaRobeId);
    }

    @Override
    public List<Roba> findAllByJedinicaMereId(long jedinicaMereId) {
        return robaRepository.findAllByJedinicaMereId(jedinicaMereId);
    }


    @Override
    public Roba save(Roba roba) {
        return robaRepository.save(roba);
    }

    @Override
    public void remove(long id) {
        robaRepository.delete(id);
    }


}
