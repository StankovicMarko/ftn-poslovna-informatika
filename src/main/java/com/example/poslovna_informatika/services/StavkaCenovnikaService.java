package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.StavkaCenovnika;
import com.example.poslovna_informatika.repositories.StavkaCenovnikaRepository;
import com.example.poslovna_informatika.serviceInterfaces.StavkaCenovnikaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StavkaCenovnikaService implements StavkaCenovnikaServiceInterface {

    private StavkaCenovnikaRepository stavkaCenovnikaRepository;

    @Autowired
    public StavkaCenovnikaService(StavkaCenovnikaRepository stavkaCenovnikaRepository) {
        this.stavkaCenovnikaRepository = stavkaCenovnikaRepository;
    }

    @Override
    public List<StavkaCenovnika> findAll() {
        return stavkaCenovnikaRepository.findAll();

    }

    @Override
    public StavkaCenovnika findOne(long id){
        return stavkaCenovnikaRepository.findOne(id);
    }

    @Override
    public List<StavkaCenovnika> findAllByCenovnikId(long cenovnikId) {
        return stavkaCenovnikaRepository.findAllByCenovnikId(cenovnikId);
    }

    @Override
    public List<StavkaCenovnika> findAllByRobaId(long robaId) {
        return stavkaCenovnikaRepository.findAllByRobaId(robaId);
    }

    @Override
    public List<StavkaCenovnika> findAllByCenaBetween(double pocetnaCena, double krajnjaCena) {
        return stavkaCenovnikaRepository.findAllByCenaBetween(pocetnaCena, krajnjaCena);
    }


    @Override
    public StavkaCenovnika save(StavkaCenovnika stavkaCenovnika) {
        return stavkaCenovnikaRepository.save(stavkaCenovnika);
    }

    @Override
    public void remove(long id) {
        stavkaCenovnikaRepository.delete(id);
    }


}
