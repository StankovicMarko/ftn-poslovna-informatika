package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.model.StavkaFakture;
import com.example.poslovna_informatika.model.StopaPDV;
import com.example.poslovna_informatika.repositories.StavkaFaktureRepository;
import com.example.poslovna_informatika.repositories.StopaPdvRepository;
import com.example.poslovna_informatika.serviceInterfaces.StavkaFaktureServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.StopaPdvServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StopaPdvService implements StopaPdvServiceInterface {

    private StopaPdvRepository stopaPdvRepository;

    @Autowired
    public StopaPdvService(StopaPdvRepository stopaPdvRepository) {
        this.stopaPdvRepository = stopaPdvRepository;
    }

    @Override
    public List<StopaPDV> findAll() {
        return stopaPdvRepository.findAll();
    }

    @Override
    public StopaPDV findOne(long id) {
        return stopaPdvRepository.findOne(id);
    }


    @Override
    public List<StopaPDV> findAllByPdvId(long pdvId) {
        return stopaPdvRepository.findAllByPdvId(pdvId);
    }

    @Override
    public List<StopaPDV> findAllByProcenat(double procenat) {
        return stopaPdvRepository.findAllByProcenat(procenat);
    }

    @Override
    public List<StopaPDV> findAllByDatumVazenja(Date datumVazenja) {
        return stopaPdvRepository.findAllByDatumVazenja(datumVazenja);
    }

    @Override
    public StopaPDV save(StopaPDV stopaPDV) {
        return stopaPdvRepository.save(stopaPDV);
    }


    @Override
    public void remove(long id) {
        stopaPdvRepository.delete(id);
    }


}
