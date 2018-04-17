package com.example.poslovna_informatika.services;

import com.example.poslovna_informatika.dto.FakturaDTO;
import com.example.poslovna_informatika.model.*;
import com.example.poslovna_informatika.repositories.FakturaRepository;
import com.example.poslovna_informatika.serviceInterfaces.FakturaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.toIntExact;

@Service
public class FakturaService implements FakturaServiceInterface {

    private FakturaRepository fakturaRepository;
    private PreduzeceService preduzeceService;
    private PoslovnaGodinaService poslovnaGodinaService;
    private PoslovniPartnerService poslovniPartnerService;
    private StavkaFaktureService stavkaFaktureService;

    @Autowired
    public FakturaService(FakturaRepository fakturaRepository, PreduzeceService preduzeceService,
                          PoslovnaGodinaService poslovnaGodinaService,
                          PoslovniPartnerService poslovniPartnerService, StavkaFaktureService stavkaFaktureService) {
        this.fakturaRepository = fakturaRepository;
        this.preduzeceService = preduzeceService;
        this.poslovnaGodinaService = poslovnaGodinaService;
        this.poslovniPartnerService = poslovniPartnerService;
        this.stavkaFaktureService = stavkaFaktureService;
    }

    @Override
    public List<Faktura> findAll() {
        return fakturaRepository.findAll();

    }

    @Override
    public Faktura findOne(long id) {
        return fakturaRepository.findOne(id);
    }

    @Override
    public Faktura findByBrojFakture(int brojFakture) {
        return fakturaRepository.findByBrojFakture(brojFakture);
    }

    @Override
    public Faktura findByStatus(char[] status) {
        return fakturaRepository.findByStatus(status);
    }

    @Override
    public List<Faktura> findAllByDatumFaktureBetween(Date pocetniDatum, Date krajnjiDatum) {
        return fakturaRepository.findAllByDatumFaktureBetween(pocetniDatum, krajnjiDatum);
    }

    @Override
    public List<Faktura> findAllByDatumValuteBetween(Date pocetniDatum, Date krajnjiDatum) {
        return fakturaRepository.findAllByDatumValuteBetween(pocetniDatum, krajnjiDatum);
    }

    @Override
    public List<Faktura> findAllByOsnovicaBetween(double pocetnaOsnovica, double krajnjaOsnovica) {
        return fakturaRepository.findAllByOsnovicaBetween(pocetnaOsnovica, krajnjaOsnovica);
    }

    @Override
    public List<Faktura> findAllByUkupanPDVBetween(double pocetniUkPdv, double krajnjiUkPdv) {
        return fakturaRepository.findAllByUkupanPDVBetween(pocetniUkPdv, krajnjiUkPdv);
    }

    @Override
    public List<Faktura> findAllByIznosZaPlacanjeBetween(double pocetniIznos, double krajnjiIznos) {
        return fakturaRepository.findAllByIznosZaPlacanjeBetween(pocetniIznos, krajnjiIznos);
    }

    @Override
    public List<Faktura> findAllByPreduzeceId(long preduzeceId) {
        return fakturaRepository.findAllByPreduzeceId(preduzeceId);
    }

    @Override
    public List<Faktura> findAllByPoslovniPartnerId(long poslovniPartnerId) {
        return fakturaRepository.findAllByPoslovniPartnerId(poslovniPartnerId);
    }


    @Override
    public List<Faktura> findAllByPoslovnaGodinaId(long poslovnaGodinaId) {
        return fakturaRepository.findAllByPoslovnaGodinaId(poslovnaGodinaId);
    }

    @Override
    public Faktura save(Faktura faktura) {
        return fakturaRepository.save(faktura);
    }

    @Override
    public void remove(long id) {
        fakturaRepository.delete(id);
    }


    public List<FakturaDTO> getFaktureByPreduzeceId(long id) {
        List<Faktura> fakture = findAllByPreduzeceId(id);
        List<FakturaDTO> fakturaDTOS = new ArrayList<>();
        for (Faktura f : fakture) {
            fakturaDTOS.add(new FakturaDTO(f));
        }
        return fakturaDTOS;
    }

    public List<FakturaDTO> getFaktureByPartnerId(long id) {
        List<Faktura> fakture = findAllByPoslovniPartnerId(id);
        List<FakturaDTO> fakturaDTOS = new ArrayList<>();
        for (Faktura f : fakture) {
            fakturaDTOS.add(new FakturaDTO(f));
        }
        return fakturaDTOS;
    }

    public FakturaDTO saveFakturaDTO(FakturaDTO fakturaDTO) {
        Preduzece preduzece = preduzeceService.findOne(fakturaDTO.getPreduzeceId());
        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findOne(fakturaDTO.getPoslovnaGodinaId());
        PoslovniPartner poslovniPartner = poslovniPartnerService.findOne(fakturaDTO.getPoslovniPartnerId());

        Faktura f = new Faktura(preduzece, poslovniPartner, poslovnaGodina, fakturaDTO.getStatus());

        f = save(f);

        f.setBrojFakture(toIntExact(f.getId()));
        f = save(f);

        return new FakturaDTO(f);
    }

    public FakturaDTO updateFakturaDTO(FakturaDTO fakturaDTO, long id) {
        Faktura f = findOne(id);

        if (f == null) {
            return null;
        }

        Preduzece preduzece = preduzeceService.findOne(fakturaDTO.getPreduzeceId());
        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findOne(fakturaDTO.getPoslovnaGodinaId());
        PoslovniPartner poslovniPartner = poslovniPartnerService.findOne(fakturaDTO.getPoslovniPartnerId());

        f.setDatumFakture(fakturaDTO.getDatumFakture());
        f.setDatumValute(fakturaDTO.getDatumValute());
        f.setOsnovica(fakturaDTO.getOsnovica());
        f.setUkupanPDV(fakturaDTO.getUkupanPdv());
        f.setIznosZaPlacanje(fakturaDTO.getIznosZaPlacanje());
        f.setStatus(fakturaDTO.getStatus());
        f.setPreduzece(preduzece);
        f.setPoslovniPartner(poslovniPartner);
        f.setPoslovnaGodina(poslovnaGodina);

        f = save(f);

        return new FakturaDTO(f);
    }

    public boolean deleteFaktura(long id) {
        Faktura f = findOne(id);
        List<StavkaFakture> stavkaFaktures = stavkaFaktureService.findAllByFakturaId(id);
        for (StavkaFakture st : stavkaFaktures) {
            stavkaFaktureService.remove(st.getId());
        }

        if (f != null) {
            remove(id);
            return true;
        }
        return false;
    }
}
