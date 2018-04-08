package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.repositories.PdvRepository;
import com.example.poslovna_informatika.serviceInterfaces.PdvServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdvService implements PdvServiceInterface {

    private PdvRepository pdvRepository;

    @Autowired
    public PdvService(PdvRepository pdvRepository) {
        this.pdvRepository = pdvRepository;
    }

    @Override
    public List<PDV> findAll() {
        return pdvRepository.findAll();

    }

    @Override
    public PDV findOne(long id){
        return pdvRepository.findOne(id);
    }

    @Override
    public PDV findByNaziv(String naziv){
        return pdvRepository.findByNaziv(naziv);
    }

    @Override
    public PDV save(PDV pdv) {
        return pdvRepository.save(pdv);
    }

    @Override
    public void remove(long id) {
        pdvRepository.delete(id);
    }


}
