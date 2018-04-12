package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.repositories.GrupaRobeRepository;
import com.example.poslovna_informatika.repositories.JedinicaMereRepository;
import com.example.poslovna_informatika.serviceInterfaces.GrupaRobeServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.JedinicaMereServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JedinicaMereService implements JedinicaMereServiceInterface {

    private JedinicaMereRepository jedinicaMereRepository;

    @Autowired
    public JedinicaMereService(JedinicaMereRepository jedinicaMereRepository) {
        this.jedinicaMereRepository = jedinicaMereRepository;
    }

    @Override
    public List<JedinicaMere> findAll() {
        return jedinicaMereRepository.findAll();

    }

    @Override
    public JedinicaMere findOne(long id){
        return jedinicaMereRepository.findOne(id);
    }


    @Override
    public JedinicaMere findByNaziv(char[] naziv) {
        return jedinicaMereRepository.findByNaziv(naziv);
    }

    @Override
    public JedinicaMere save(JedinicaMere jedinicaMere) {
        return jedinicaMereRepository.save(jedinicaMere);
    }

    @Override
    public void remove(long id) {
        jedinicaMereRepository.delete(id);
    }


}