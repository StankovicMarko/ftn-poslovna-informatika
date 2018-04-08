package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class PreduzeceDTO {

    private long id;
    private String naziv;
    private String adresa;
    private int pib;
    private String telefon;
    private String email;
    private String logoPath;
    private long mestoId;


    public PreduzeceDTO() {
    }

    public PreduzeceDTO(long id, String naziv, String adresa, int pib, String telefon, String email,
                        String logoPath, long mestoId) {
        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.pib = pib;
        this.telefon = telefon;
        this.email = email;
        this.logoPath = logoPath;
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
        this.mestoId = preduzece.getMesto().getId();
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

    public long getMestoId() {
        return mestoId;
    }

    public void setMestoId(long mestoId) {
        this.mestoId = mestoId;
    }
}

