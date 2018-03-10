package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PoslovniPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String naziv;

    @Column(nullable = false)
    private String adresa;

    @Column(nullable = false)
    private char[] vrsta = new char[2];

    @OneToMany
    private List<Faktura> fakture;

    @ManyToOne
    @JoinColumn(name = "mesto")
    private Mesto mesto;

    @ManyToOne
    @JoinColumn(name = "preduzece")
    private Preduzece preduzece;

    public PoslovniPartner() {
    }

    public PoslovniPartner(String naziv, String adresa, char[] vrsta, Mesto mesto, Preduzece preduzece) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.vrsta = vrsta;
        this.mesto = mesto;
        this.preduzece = preduzece;
        this.fakture = new ArrayList<>();
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

    public List<Faktura> getFakture() {
        return fakture;
    }

    public void setFakture(List<Faktura> fakture) {
        this.fakture = fakture;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public Preduzece getPreduzece() {
        return preduzece;
    }

    public void setPreduzece(Preduzece preduzece) {
        this.preduzece = preduzece;
    }
}
