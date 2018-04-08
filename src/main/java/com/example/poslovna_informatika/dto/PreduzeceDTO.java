package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class PreduzeceDTO implements Serializable {


    private long id;
    private String naziv;
    private String adresa;
    private int pib;
    private String telefon;
    private String email;
    private String logoPath;
    private List<Long> grupeRobaIds;
    private List<Long> faktureIds;
    private List<Long> poslovniPartneriIds;
    private List<Long> cenovniciIds;
    private long mestoId;


    public PreduzeceDTO() {
    }

    public PreduzeceDTO(long id, String naziv, String adresa, int pib, String telefon, String email,
                        String logoPath, List<Long> grupeRobaIds, List<Long> faktureIds,
                        List<Long> poslovniPartneriIds, List<Long> cenovniciIds, long mestoId) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.pib = pib;
        this.telefon = telefon;
        this.email = email;
        this.logoPath = logoPath;
        this.grupeRobaIds = grupeRobaIds;
        this.faktureIds = faktureIds;
        this.poslovniPartneriIds = poslovniPartneriIds;
        this.cenovniciIds = cenovniciIds;
        this.mestoId = mestoId;
    }

    public PreduzeceDTO(Preduzece preduzece) {
        this.id = preduzece.getId();
        this.naziv = preduzece.getNaziv();
        this.adresa = preduzece.getAdresa();
        this.pib = preduzece.getPib();
        this.telefon = preduzece.getTelefon();
        this.email = preduzece.getEmail();
        this.logoPath = preduzece.getLogoPath();
        this.grupeRobaIds = makeGrupeIds(preduzece.getGrupeRoba());
        this.faktureIds = makeFaktureIds(preduzece.getFakture());
        this.poslovniPartneriIds = makePartneriIds(preduzece.getPoslovniPartneri());
        this.cenovniciIds = makeCenovniciIds(preduzece.getCenovnici());
        this.mestoId = preduzece.getMesto().getId();
        }

    private List<Long> makeGrupeIds(List<GrupaRobe> grupaRobe){
        return grupaRobe.stream().map(x -> x.getId()).collect(Collectors.toList());

    }


    private List<Long> makeFaktureIds(List<Faktura> fakturas){
        return fakturas.stream().map(x -> x.getId()).collect(Collectors.toList());

    }


    private List<Long> makePartneriIds(List<PoslovniPartner> poslovniPartners){
        return poslovniPartners.stream().map(x -> x.getId()).collect(Collectors.toList());

    }


    private List<Long> makeCenovniciIds(List<Cenovnik> cenovniks){
        return cenovniks.stream().map(x -> x.getId()).collect(Collectors.toList());

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

    public int getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public List<Long> getGrupeRobaIds() {
        return grupeRobaIds;
    }

    public void setGrupeRobaIds(List<Long> grupeRobaIds) {
        this.grupeRobaIds = grupeRobaIds;
    }

    public List<Long> getFaktureIds() {
        return faktureIds;
    }

    public void setFaktureIds(List<Long> faktureIds) {
        this.faktureIds = faktureIds;
    }

    public List<Long> getPoslovniPartneriIds() {
        return poslovniPartneriIds;
    }

    public void setPoslovniPartneriIds(List<Long> poslovniPartneriIds) {
        this.poslovniPartneriIds = poslovniPartneriIds;
    }

    public List<Long> getCenovniciIds() {
        return cenovniciIds;
    }

    public void setCenovniciIds(List<Long> cenovniciIds) {
        this.cenovniciIds = cenovniciIds;
    }

    public long getMestoId() {
        return mestoId;
    }

    public void setMestoId(long mestoId) {
        this.mestoId = mestoId;
    }
}

