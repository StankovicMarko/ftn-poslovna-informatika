package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.dto.StopaPdvDTO;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.StopaPDV;
import com.example.poslovna_informatika.repositories.StopaPdvRepository;
import com.example.poslovna_informatika.serviceInterfaces.StopaPdvServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StopaPdvService implements StopaPdvServiceInterface {

    private StopaPdvRepository stopaPdvRepository;
    private PdvService pdvService;

    @Autowired
    public StopaPdvService(StopaPdvRepository stopaPdvRepository, PdvService pdvService) {
        this.stopaPdvRepository = stopaPdvRepository;
        this.pdvService = pdvService;
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


    public List<StopaPdvDTO> getAllStopaPdv() {
        List<StopaPDV> stopaPDVS = findAll();
        List<StopaPdvDTO> stopaPdvDTOS = new ArrayList<StopaPdvDTO>();
        for (StopaPDV st : stopaPDVS) {
            stopaPdvDTOS.add(new StopaPdvDTO(st));
        }
        return stopaPdvDTOS;
    }

    public StopaPdvDTO saveStopaPdv(StopaPdvDTO stopaPdvDTO) {
        PDV pdv = pdvService.findOne(stopaPdvDTO.getPdvId());

        StopaPDV stopa = new StopaPDV(stopaPdvDTO.getProcenat(), stopaPdvDTO.getDatumVazenja(), pdv);
        stopa = save(stopa);
        return new StopaPdvDTO(stopa);
    }

    public StopaPdvDTO updateStopaPdv(StopaPdvDTO stopaPdvDTO, long id) {
        StopaPDV stopa = findOne(id);

        if (stopa == null) {
            return null;
        }
        PDV pdv = pdvService.findOne(stopaPdvDTO.getPdvId());

        stopa.setDatumVazenja(stopaPdvDTO.getDatumVazenja());
        stopa.setProcenat(stopaPdvDTO.getProcenat());
        stopa.setPdv(pdv);

        stopa = save(stopa);
        return new StopaPdvDTO(stopa);
    }

    public boolean deleteStopaPdv(long id) {
        StopaPDV stopa = findOne(id);
        if (stopa != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
