package com.example.poslovna_informatika.services;


import com.example.poslovna_informatika.dto.StavkaFaktureDTO;
import com.example.poslovna_informatika.model.Faktura;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaFakture;
import com.example.poslovna_informatika.repositories.FakturaRepository;
import com.example.poslovna_informatika.repositories.StavkaFaktureRepository;
import com.example.poslovna_informatika.serviceInterfaces.StavkaFaktureServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StavkaFaktureService implements StavkaFaktureServiceInterface {

    private StavkaFaktureRepository stavkaFaktureRepository;
    private RobaService robaService;
    private FakturaRepository fakturaRepository;

    @Autowired
    public StavkaFaktureService(StavkaFaktureRepository stavkaCenovnikaRepository, FakturaRepository fakturaRepository, RobaService robaService) {
        this.stavkaFaktureRepository = stavkaCenovnikaRepository;
        this.fakturaRepository = fakturaRepository;
        this.robaService = robaService;
    }

    @Override
    public List<StavkaFakture> findAll() {
        return stavkaFaktureRepository.findAll();

    }

    @Override
    public StavkaFakture findOne(long id) {
        return stavkaFaktureRepository.findOne(id);
    }

    @Override
    public List<StavkaFakture> findAllByFakturaId(long fakturaId) {
        return stavkaFaktureRepository.findAllByFakturaId(fakturaId);
    }

    @Override
    public List<StavkaFakture> findAllByRobaIn(long robaId) {
        return stavkaFaktureRepository.findAllByRobaIn(robaId);
    }

    @Override
    public List<StavkaFakture> findAllByKolicinaBetween(int pocetnaKolicina, int krajnjaKolicina) {
        return stavkaFaktureRepository.findAllByKolicinaBetween(pocetnaKolicina, krajnjaKolicina);
    }

    @Override
    public List<StavkaFakture> findAllByJedinicnaCenaBetween(double pocetnaJedCena, double krajnjaJedCena) {
        return stavkaFaktureRepository.findAllByJedinicnaCenaBetween(pocetnaJedCena, krajnjaJedCena);
    }

    @Override
    public List<StavkaFakture> findAllByRabatBetween(double pocetniRabat, double krajnjiRabat) {
        return stavkaFaktureRepository.findAllByRabatBetween(pocetniRabat, krajnjiRabat);
    }

    @Override
    public List<StavkaFakture> findAllByOsnovicaZaPDVBetween(double pocetnaOsnovica, double krajnjaOsnovica) {
        return stavkaFaktureRepository.findAllByOsnovicaZaPDVBetween(pocetnaOsnovica, krajnjaOsnovica);
    }

    @Override
    public List<StavkaFakture> findAllByProcenatPDVBetween(double pocetniProcPdv, double krajnjiProcPdv) {
        return stavkaFaktureRepository.findAllByProcenatPDVBetween(pocetniProcPdv, krajnjiProcPdv);
    }

    @Override
    public List<StavkaFakture> findAllByIznosPDVBetween(double pocetniPdv, double krajnjiPdv) {
        return stavkaFaktureRepository.findAllByIznosPDVBetween(pocetniPdv, krajnjiPdv);
    }

    @Override
    public List<StavkaFakture> findAllByIznosStavkeBetween(double pocetniIznosStavke, double krajnjiIznosStavke) {
        return stavkaFaktureRepository.findAllByIznosStavkeBetween(pocetniIznosStavke, krajnjiIznosStavke);
    }

    @Override
    public StavkaFakture save(StavkaFakture stavkaFakture) {
        return stavkaFaktureRepository.save(stavkaFakture);
    }

    @Override
    public void remove(long id) {
        stavkaFaktureRepository.delete(id);
    }


    public List<StavkaFaktureDTO> getAllStavkaFaktureById(long id) {
        List<StavkaFakture> stavkaFaktures = findAllByFakturaId(id);
        List<StavkaFaktureDTO> stavkaFaktureDTOS = new ArrayList<>();
        for (StavkaFakture sf : stavkaFaktures) {
            stavkaFaktureDTOS.add(new StavkaFaktureDTO(sf));
        }
        return stavkaFaktureDTOS;
    }

    public List<StavkaFaktureDTO> getAllStavkaFakture() {
        List<StavkaFakture> stavkaFaktures = findAll();
        List<StavkaFaktureDTO> stavkaFaktureDTOS = new ArrayList<>();
        for (StavkaFakture sf : stavkaFaktures) {
            stavkaFaktureDTOS.add(new StavkaFaktureDTO(sf));
        }
        return stavkaFaktureDTOS;
    }

    public StavkaFaktureDTO saveStavkaFakture(StavkaFaktureDTO stavkaFaktureDTO) {
        Faktura f = fakturaRepository.findOne(stavkaFaktureDTO.getFakturaId());
        Roba r = robaService.findOne(stavkaFaktureDTO.getRobaId());


        StavkaFakture sf = new StavkaFakture(stavkaFaktureDTO.getKolicina(), stavkaFaktureDTO.getJedinicnaCena(),
                stavkaFaktureDTO.getRabat(), stavkaFaktureDTO.getOsnovicaZaPDV(), stavkaFaktureDTO.getProcenatPDV(),
                stavkaFaktureDTO.getIznosPDV(), stavkaFaktureDTO.getIznosStavke(), r, f);

        f.setOsnovica(f.getOsnovica() + stavkaFaktureDTO.getOsnovicaZaPDV());
        f.setUkupanPDV(f.getUkupanPDV() + stavkaFaktureDTO.getIznosPDV());
        f.setIznosZaPlacanje(f.getIznosZaPlacanje() + stavkaFaktureDTO.getIznosStavke());

        fakturaRepository.save(f);

        sf = save(sf);

        return new StavkaFaktureDTO(sf);
    }

    public StavkaFaktureDTO updateStavkaFakture(StavkaFaktureDTO stavkaFaktureDTO, long id) {
        StavkaFakture sf = findOne(id);

        if (sf == null) {
            return null;
        }

        Faktura f = fakturaRepository.findOne(stavkaFaktureDTO.getFakturaId());
        Roba r = robaService.findOne(stavkaFaktureDTO.getRobaId());

        sf.setKolicina(stavkaFaktureDTO.getKolicina());
        sf.setJedinicnaCena(stavkaFaktureDTO.getJedinicnaCena());
        sf.setRabat(stavkaFaktureDTO.getRabat());
        sf.setOsnovicaZaPDV(stavkaFaktureDTO.getOsnovicaZaPDV());
        sf.setProcenatPDV(stavkaFaktureDTO.getProcenatPDV());
        sf.setIznosPDV(stavkaFaktureDTO.getIznosPDV());
        sf.setIznosStavke(stavkaFaktureDTO.getIznosStavke());
        sf.setRoba(r);
        sf.setFaktura(f);

        sf = save(sf);
        return new StavkaFaktureDTO(sf);
    }

    public boolean deleteStavkaFakture(long id) {
        StavkaFakture sf = findOne(id);

        Faktura f = fakturaRepository.findOne(sf.getFaktura().getId());
        f.setOsnovica(f.getOsnovica() - sf.getOsnovicaZaPDV());
        f.setUkupanPDV(f.getUkupanPDV() - sf.getIznosPDV());
        f.setIznosZaPlacanje(f.getIznosZaPlacanje() - sf.getIznosStavke());

        fakturaRepository.save(f);

        if (sf != null) {
            remove(id);
            return true;
        }

        return false;
    }
}
