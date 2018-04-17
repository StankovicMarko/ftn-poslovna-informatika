package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.PdvDTO;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.repositories.PdvRepository;
import com.example.poslovna_informatika.serviceInterfaces.PdvServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public PDV findOne(long id) {
        return pdvRepository.findOne(id);
    }

    @Override
    public PDV findByNaziv(String naziv) {
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


    public List<PdvDTO> getAllPdvs() {
        List<PDV> pdvs = findAll();
        List<PdvDTO> pdvDTOS = new ArrayList<>();
        for (PDV pdv : pdvs) {
            pdvDTOS.add(new PdvDTO(pdv));
        }
        return pdvDTOS;
    }

    public PdvDTO savePdvDto(PdvDTO pdvDTO) {
        PDV pdv = new PDV(pdvDTO.getNaziv());
        pdv = save(pdv);
        return new PdvDTO(pdv);
    }

    public PdvDTO updatePdv(PdvDTO pdvDTO, long id) {
        PDV pdv = findOne(id);

        if (pdv == null) {
            return null;
        }
        pdv.setNaziv(pdvDTO.getNaziv());

        pdv = save(pdv);
        return new PdvDTO(pdv);
    }

    public boolean deletePdv(long id) {
        PDV pdv = findOne(id);
        if (pdv != null) {
            remove(id);
            return true;
        } else {
            return false;
        }
    }
}
