package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.GrupaRobeDTO;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.GrupaRobeRepository;
import com.example.poslovna_informatika.serviceInterfaces.GrupaRobeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupaRobeService implements GrupaRobeServiceInterface {

    private GrupaRobeRepository grupaRobeRepository;
    private PreduzeceService preduzeceService;
    private PdvService pdvService;

    @Autowired
    public GrupaRobeService(GrupaRobeRepository grupaRobeRepository, PreduzeceService preduzeceService, PdvService pdvService) {
        this.grupaRobeRepository = grupaRobeRepository;
        this.preduzeceService = preduzeceService;
        this.pdvService = pdvService;
    }

    @Override
    public List<GrupaRobe> findAll() {
        return grupaRobeRepository.findAll();

    }

    @Override
    public GrupaRobe findOne(long id) {
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

    public List<GrupaRobeDTO> getGrupaRobeByPredAndPdv(long predId, long pdvId) {
        List<GrupaRobe> grupaRobesPred = findAllByPreduzeceId(predId);
        List<GrupaRobe> grupaRobesPdv = findAllByPreduzeceId(pdvId);
        grupaRobesPred.retainAll(grupaRobesPdv);

        List<GrupaRobeDTO> grupaRobeDTOS = new ArrayList<>();
        for (GrupaRobe gr : grupaRobesPred) {
            grupaRobeDTOS.add(new GrupaRobeDTO(gr));
        }

        return grupaRobeDTOS;
    }

    public List<GrupaRobeDTO> getAllGrupaRobeById(long id) {
        List<GrupaRobe> grupaRobes;
        if (id == 0) {
            grupaRobes = findAll();
        } else {
            grupaRobes = findAllByPreduzeceId(id);
        }
        List<GrupaRobeDTO> grupaRobeDTOS = new ArrayList<>();
        for (GrupaRobe gr : grupaRobes) {
            grupaRobeDTOS.add(new GrupaRobeDTO(gr));
        }
        return grupaRobeDTOS;
    }

    public List<GrupaRobeDTO> getAllGrupaRobe() {
        return getAllGrupaRobeById(0);
    }

    public GrupaRobeDTO saveGrupaRobeDto(GrupaRobeDTO grupaRobeDTO) {
        Preduzece preduzece = preduzeceService.findOne(grupaRobeDTO.getPreduzeceId());
        PDV pdv = pdvService.findOne(grupaRobeDTO.getPdvId());

        GrupaRobe gr = new GrupaRobe(grupaRobeDTO.getNaziv(), new ArrayList<>(), preduzece, pdv);

        return new GrupaRobeDTO(save(gr));
    }

    public GrupaRobeDTO updateGrupaRobe(GrupaRobeDTO grupaRobeDTO, long id) {
        GrupaRobe gr = findOne(id);

        if (gr == null) {
            return null;
        }

        Preduzece preduzece = preduzeceService.findOne(grupaRobeDTO.getPreduzeceId());
        PDV pdv = pdvService.findOne(grupaRobeDTO.getPdvId());


        gr.setNaziv(grupaRobeDTO.getNaziv());
        gr.setPdv(pdv);
        gr.setPreduzece(preduzece);

        return new GrupaRobeDTO(save(gr));
    }

    public boolean deleteGrupaRobe(long id) {
        GrupaRobe gr = findOne(id);
        if (gr != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
