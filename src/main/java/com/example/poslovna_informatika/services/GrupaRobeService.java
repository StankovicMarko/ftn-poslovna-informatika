package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.GrupaRobeDTO;
import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.GrupaRobeRepository;
import com.example.poslovna_informatika.serviceInterfaces.GrupaRobeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<GrupaRobe> findAll(Pageable pageable) {
        return grupaRobeRepository.findAll(pageable);

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
    public Page<GrupaRobe> findAllByPreduzeceId(long preduzeceId, Pageable pageable) {
        return grupaRobeRepository.findAllByPreduzeceId(preduzeceId, pageable);
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

    public List<GrupaRobeDTO> getGrupaRobeByPredAndPdv(long predId, long pdvId, Pageable pageable) {
        Page<GrupaRobe> grupaRoba = grupaRobeRepository.findAllByPreduzeceIdAndPdvId(predId, pdvId, pageable);

        List<GrupaRobeDTO> grupaRobeDTOS = new ArrayList<>();
        for (GrupaRobe gr : grupaRoba) {
            grupaRobeDTOS.add(new GrupaRobeDTO(gr));
        }

        return grupaRobeDTOS;
    }

    public List<GrupaRobeDTO> getGrupaRobeByPred(long predId, Pageable pageable) {
        Page<GrupaRobe> grupaRoba = grupaRobeRepository.findAllByPreduzeceId(predId, pageable);

        List<GrupaRobeDTO> grupaRobeDTOS = new ArrayList<>();
        for (GrupaRobe gr : grupaRoba) {
            grupaRobeDTOS.add(new GrupaRobeDTO(gr));
        }

        return grupaRobeDTOS;
    }

    public List<GrupaRobeDTO> getAllGrupaRobeById(long id, Pageable pageable) {
        Page<GrupaRobe> grupaRobes;
        if (id == 0) {
            grupaRobes = findAll(pageable);
        } else {
            grupaRobes = findAllByPreduzeceId(id, pageable);
        }
        List<GrupaRobeDTO> grupaRobeDTOS = new ArrayList<>();
        for (GrupaRobe gr : grupaRobes) {
            grupaRobeDTOS.add(new GrupaRobeDTO(gr));
        }
        return grupaRobeDTOS;
    }

    public List<GrupaRobeDTO> getAllGrupaRobe(Pageable pageable) {
        return getAllGrupaRobeById(0, pageable);
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
