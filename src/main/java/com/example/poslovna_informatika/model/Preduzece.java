package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Preduzece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 12)
    private String naziv;

    @Column(nullable = false)
    private String adresa;

    @Column(nullable = false, unique = true, length = 10)
    private int pib;

    @Column(length = 10)
    private String telefon;

    @Column
    private String email;

    @Column
    private String logoPath;

    @OneToMany
    private List<GrupaRobe> grupeRoba;

    @OneToMany
    private List<Faktura> fakture;

    @OneToMany
    private List<PoslovniPartner> poslovniPartneri;

    @OneToMany
    private List<Cenovnik> cenovnici;

    @ManyToOne
    @JoinColumn(name = "mesto")
    private Mesto mesto;

    public Preduzece() {
    }

    public Preduzece(String naziv, String adresa, int pib, String telefon, String email, String logoPath, Mesto mesto) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.pib = pib;
        this.telefon = telefon;
        this.email = email;
        this.logoPath = logoPath;
        this.mesto = mesto;
        this.grupeRoba = new ArrayList<>();
        this.fakture = new ArrayList<>();
        this.poslovniPartneri = new ArrayList<>();
        this.cenovnici = new ArrayList<>();
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

    public List<GrupaRobe> getGrupeRoba() {
        return grupeRoba;
    }

    public void setGrupeRoba(List<GrupaRobe> grupeRoba) {
        this.grupeRoba = grupeRoba;
    }

    public List<Faktura> getFakture() {
        return fakture;
    }

    public void setFakture(List<Faktura> fakture) {
        this.fakture = fakture;
    }

    public List<PoslovniPartner> getPoslovniPartneri() {
        return poslovniPartneri;
    }

    public void setPoslovniPartneri(List<PoslovniPartner> poslovniPartneri) {
        this.poslovniPartneri = poslovniPartneri;
    }

    public List<Cenovnik> getCenovnici() {
        return cenovnici;
    }

    public void setCenovnici(List<Cenovnik> cenovnici) {
        this.cenovnici = cenovnici;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
}
