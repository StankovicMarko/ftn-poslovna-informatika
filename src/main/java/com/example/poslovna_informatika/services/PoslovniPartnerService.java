package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.PoslovnaGodina;
import com.example.poslovna_informatika.model.PoslovniPartner;
import com.example.poslovna_informatika.repositories.PoslovnaGodinaRepository;
import com.example.poslovna_informatika.repositories.PoslovniPartnerRepository;
import com.example.poslovna_informatika.serviceInterfaces.PoslovnaGodinaServiceInterface;
import com.example.poslovna_informatika.serviceInterfaces.PoslovniPartnerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoslovniPartnerService implements PoslovniPartnerServiceInterface {

    private PoslovniPartnerRepository poslovniPartnerRepository;

    @Autowired
    public PoslovniPartnerService(PoslovniPartnerRepository poslovniPartnerRepository) {
        this.poslovniPartnerRepository = poslovniPartnerRepository;
    }

    @Override
    public List<PoslovniPartner> findAll() {
        return poslovniPartnerRepository.findAll();

    }

    @Override
    public PoslovniPartner findOne(long id){
        return poslovniPartnerRepository.findOne(id);
    }

    @Override
    public PoslovniPartner findByNaziv(String naziv){
        return poslovniPartnerRepository.findByNaziv(naziv);
    }

    @Override
    public List<PoslovniPartner> findAllByAdresa(String adresa){
        return poslovniPartnerRepository.findAllByAdresa(adresa);
    }

    @Override
    public List<PoslovniPartner> findAllByVrsta(char[] vrsta){
        return poslovniPartnerRepository.findAllByVrsta(vrsta);
    }

    @Override
    public List<PoslovniPartner> findAllByMestoId(long mestoId){
        return poslovniPartnerRepository.findAllByMestoId(mestoId);
    }

    @Override
    public List<PoslovniPartner> findAllByPreduzeceId(long preduzeceId){
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


}
