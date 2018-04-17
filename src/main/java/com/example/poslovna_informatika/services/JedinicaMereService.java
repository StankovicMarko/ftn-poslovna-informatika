package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.JedinicaMereDTO;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.repositories.JedinicaMereRepository;
import com.example.poslovna_informatika.serviceInterfaces.JedinicaMereServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public JedinicaMere findOne(long id) {
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


    public List<JedinicaMereDTO> getAllJedinicaMere() {
        List<JedinicaMere> jediniceMera = findAll();
        List<JedinicaMereDTO> jedinicaMereDTOS = new ArrayList<>();
        for (JedinicaMere jm : jediniceMera) {
            jedinicaMereDTOS.add(new JedinicaMereDTO(jm));
        }

        return jedinicaMereDTOS;
    }

    public JedinicaMere saveJedinicaMere(JedinicaMere jedinicaMere) {
        JedinicaMere jm = new JedinicaMere(jedinicaMere.getNaziv());
        return save(jm);
    }

    public JedinicaMereDTO updateJedinicaMere(JedinicaMereDTO jedinicaMereDTO, long id) {
        JedinicaMere jm = findOne(id);

        if (jm == null) {
            return null;
        }

        jm.setNaziv(jedinicaMereDTO.getNaziv());

        return new JedinicaMereDTO(save(jm));
    }

    public boolean deleteJedinicaMere(long id) {
        JedinicaMere jm = findOne(id);
        if (jm != null) {
            remove(id);
            return true;
        }
        return false;
    }

}
