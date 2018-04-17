package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.CenovnikDTO;
import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.CenovnikRepository;
import com.example.poslovna_informatika.serviceInterfaces.CenovnikServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CenovnikService implements CenovnikServiceInterface {

    private CenovnikRepository cenovnikRepository;
    private PreduzeceService preduzeceService;

    @Autowired
    public CenovnikService(CenovnikRepository cenovnikRepository, PreduzeceService preduzeceService) {
        this.cenovnikRepository = cenovnikRepository;
        this.preduzeceService = preduzeceService;
    }

    @Override
    public List<Cenovnik> findAll() {
        return cenovnikRepository.findAll();
    }

    @Override
    public Cenovnik findOne(long id) {
        return cenovnikRepository.findOne(id);
    }

    @Override
    public List<Cenovnik> findAllByPreduzeceId(long preduzeceId) {
        return cenovnikRepository.findAllByPreduzeceId(preduzeceId);
    }

    @Override
    public List<Cenovnik> findAllByDatumVazenjaBetween(Date pocetniDatum, Date kranjiDatum) {
        return cenovnikRepository.findAllByDatumVazenjaBetween(pocetniDatum, kranjiDatum);
    }

    @Override
    public Cenovnik save(Cenovnik cenovnik) {
        return cenovnikRepository.save(cenovnik);
    }

    @Override
    public void remove(long id) {
        cenovnikRepository.delete(id);
    }

    public List<CenovnikDTO> getCenovniciDTObyId(long id) {
        List<Cenovnik> cenovnici = findAllByPreduzeceId(id);
        List<CenovnikDTO> cenovniciDTOS = new ArrayList<CenovnikDTO>();
        for (Cenovnik c : cenovnici) {
            cenovniciDTOS.add(new CenovnikDTO(c));
        }

        return cenovniciDTOS;
    }

    public CenovnikDTO saveCenovnikDTO(CenovnikDTO cenovnikDTO) {
        Preduzece preduzece = preduzeceService.findOne(cenovnikDTO.getPreduzeceId());
        Cenovnik c = new Cenovnik(cenovnikDTO.getDatumVazenja(), preduzece);
        return new CenovnikDTO(save(c));
    }

    public CenovnikDTO updateCenovnik(CenovnikDTO cenovnikDTO, long id) {
        Cenovnik c = findOne(id);

        if (c == null) {
            return null;
        }

        Preduzece p = preduzeceService.findOne(cenovnikDTO.getPreduzeceId());

        c.setDatumVazenja(cenovnikDTO.getDatumVazenja());
        c.setPreduzece(p);

        return new CenovnikDTO(save(c));
    }

    public boolean deleteCenovnik(long id) {
        Cenovnik c = findOne(id);
        if (c != null) {
            remove(id);
            return true;
        } else {
            return false;
        }
    }
}
