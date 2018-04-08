package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PoslovniPartnerDTO implements Serializable {



    private long id;
    private String naziv;
    private String adresa;
    private char[] vrsta = new char[2];
    private List<Long> faktureIds;
    private long mestoId;
    private long preduzeceId;

    public PoslovniPartnerDTO() {
    }

    public PoslovniPartnerDTO(long id, String naziv, String adresa, char[] vrsta, List<Long> faktureIds,
                              long mestoId, long preduzeceId) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.vrsta = vrsta;
        this.faktureIds = faktureIds;
        this.mestoId = mestoId;
        this.preduzeceId = preduzeceId;
    }

    public PoslovniPartnerDTO(PoslovniPartner poslovniPartner) {
        this.id = poslovniPartner.getId();
        this.naziv = poslovniPartner.getNaziv();
        this.adresa = poslovniPartner.getAdresa();
        this.vrsta = poslovniPartner.getVrsta();
        this.faktureIds = makeFaktureIds(poslovniPartner.getFakture());
        this.mestoId = poslovniPartner.getMesto().getId();
        this.preduzeceId = poslovniPartner.getPreduzece().getId();
    }

    private List<Long> makeFaktureIds(List<Faktura> fakturas){
        return fakturas.stream().map(x -> x.getId()).collect(Collectors.toList());

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public char[] getVrsta() {
        return vrsta;
    }

    public void setVrsta(char[] vrsta) {
        this.vrsta = vrsta;
    }

    public List<Long> getFaktureIds() {
        return faktureIds;
    }

    public void setFaktureIds(List<Long> faktureIds) {
        this.faktureIds = faktureIds;
    }

    public long getMestoId() {
        return mestoId;
    }

    public void setMestoId(long mestoId) {
        this.mestoId = mestoId;
    }

    public long getPreduzeceId() {
        return preduzeceId;
    }

    public void setPreduzeceId(long preduzeceId) {
        this.preduzeceId = preduzeceId;
    }
}

