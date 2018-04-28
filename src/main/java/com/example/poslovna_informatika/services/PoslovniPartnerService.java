package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.dto.PoslovniPartnerDTO;
import com.example.poslovna_informatika.model.Mesto;
import com.example.poslovna_informatika.model.PoslovniPartner;
import com.example.poslovna_informatika.model.Preduzece;
import com.example.poslovna_informatika.repositories.PoslovniPartnerRepository;
import com.example.poslovna_informatika.serviceInterfaces.PoslovniPartnerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoslovniPartnerService implements PoslovniPartnerServiceInterface {

    private PoslovniPartnerRepository poslovniPartnerRepository;
    private MestoService mestoService;
    private PreduzeceService preduzeceService;

    @Autowired
    public PoslovniPartnerService(PoslovniPartnerRepository poslovniPartnerRepository, MestoService mestoService, PreduzeceService preduzeceService) {
        this.poslovniPartnerRepository = poslovniPartnerRepository;
        this.mestoService = mestoService;
        this.preduzeceService = preduzeceService;
    }

    @Override
    public List<PoslovniPartner> findAll() {
        return poslovniPartnerRepository.findAll();

    }

    @Override
    public PoslovniPartner findOne(long id) {
        return poslovniPartnerRepository.findOne(id);
    }

    @Override
    public PoslovniPartner findByNaziv(String naziv) {
        return poslovniPartnerRepository.findByNaziv(naziv);
    }

    @Override
    public List<PoslovniPartner> findAllByAdresa(String adresa) {
        return poslovniPartnerRepository.findAllByAdresa(adresa);
    }

    @Override
    public List<PoslovniPartner> findAllByVrsta(char[] vrsta) {
        return poslovniPartnerRepository.findAllByVrsta(vrsta);
    }

    @Override
    public List<PoslovniPartner> findAllByMestoId(long mestoId) {
        return poslovniPartnerRepository.findAllByMestoId(mestoId);
    }

    @Override
    public List<PoslovniPartner> findAllByPreduzeceId(long preduzeceId) {
        return poslovniPartnerRepository.findAllByPreduzeceId(preduzeceId);
    }

    @Override
    public PoslovniPartner save(PoslovniPartner poslovniPartner) {
        return poslovniPartnerRepository.save(poslovniPartner);
    }

    @Override
    public void remove(long id) {
        poslovniPartnerRepository.delete(id);
    }


    public List<PoslovniPartnerDTO> getAllPartners() {
        List<PoslovniPartner> partners = findAll();
        List<PoslovniPartnerDTO> partnersDTO = new ArrayList<>();
        for (PoslovniPartner p : partners) {
            partnersDTO.add(new PoslovniPartnerDTO(p));
        }
        return partnersDTO;
    }

    public PoslovniPartnerDTO getPartnerById(long id) {
        PoslovniPartner poslovniPartner = findOne(id);
        return new PoslovniPartnerDTO(poslovniPartner);
    }

    public List<PoslovniPartnerDTO> getPartnersByPreduzece(long id) {
        List<PoslovniPartner> poslovniPartners = findAllByPreduzeceId(id);
        List<PoslovniPartnerDTO> poslovniPartnerDTOS = new ArrayList<>();
        for (PoslovniPartner pp : poslovniPartners) {
            poslovniPartnerDTOS.add(new PoslovniPartnerDTO(pp));
        }
        return poslovniPartnerDTOS;
    }

    public List<PoslovniPartnerDTO> getPartnerByPreduzeceAndMesto(long preduzeceId, long mestoId) {
        List<PoslovniPartner> poslovniPartners =
                poslovniPartnerRepository.findAllByPreduzeceIdAndMestoId(preduzeceId, mestoId);
        List<PoslovniPartnerDTO> poslovniPartnerDTOS = new ArrayList<>();
        for (PoslovniPartner pp : poslovniPartners) {
            poslovniPartnerDTOS.add(new PoslovniPartnerDTO(pp));
        }
        return poslovniPartnerDTOS;
    }

    public PoslovniPartnerDTO savePoslovniPartner(PoslovniPartnerDTO poslovniPartnerDTO) {
        Preduzece preduzece = preduzeceService.findOne(poslovniPartnerDTO.getPreduzeceId());
        Mesto mesto = mestoService.findOne(poslovniPartnerDTO.getMestoId());

        PoslovniPartner pp = new PoslovniPartner(poslovniPartnerDTO.getNaziv(), poslovniPartnerDTO.getAdresa(),
                poslovniPartnerDTO.getVrsta(), mesto, preduzece);

        pp = save(pp);

        return new PoslovniPartnerDTO(pp);
    }

    public PoslovniPartnerDTO updatePoslovniPartner(PoslovniPartnerDTO poslovniPartnerDTO, long id) {
        PoslovniPartner pp = findOne(id);

        if (pp == null) {
            return null;
        }

        Preduzece preduzece = preduzeceService.findOne(poslovniPartnerDTO.getPreduzeceId());
        Mesto mesto = mestoService.findOne(poslovniPartnerDTO.getMestoId());

        pp.setNaziv(poslovniPartnerDTO.getNaziv());
        pp.setAdresa(poslovniPartnerDTO.getAdresa());
        pp.setVrsta(poslovniPartnerDTO.getVrsta());
        pp.setMesto(mesto);
        pp.setPreduzece(preduzece);

        pp = save(pp);

        return new PoslovniPartnerDTO(pp);
    }

    public boolean deletePoslovniPartner(long id) {
        PoslovniPartner pp = findOne(id);
        if (pp != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
