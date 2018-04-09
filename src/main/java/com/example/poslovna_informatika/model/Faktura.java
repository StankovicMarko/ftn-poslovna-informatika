package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Faktura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true)
    private int brojFakture;

    @Column(nullable = false)
    private Date datumFakture;

    @Column(nullable = false)
    private Date datumValute;

    @Column(nullable = false)
    private double osnovica;

    @Column(nullable = false)
    private double ukupanPDV;

    @Column(nullable = false)
    private double iznosZaPlacanje;

    @Column(nullable = false)
    private char[] status = new char[2];

    @OneToMany
    private List<StavkaFakture> stavkeFakture;

    @ManyToOne
    @JoinColumn(name = "preduzece")
    private Preduzece preduzece;

    @ManyToOne
    @JoinColumn(name = "poslovni_partner")
    private PoslovniPartner poslovniPartner;

    @ManyToOne
    @JoinColumn(name = "poslovna_godina")
    private PoslovnaGodina poslovnaGodina;

    public Faktura() {
    }

    public Faktura(Date datumFakture, Date datumValute, double osnovica, double ukupanPDV,
                   double iznosZaPlacanje, char[] status, Preduzece preduzece, PoslovniPartner poslovniPartner,
                   PoslovnaGodina poslovnaGodina) {
        this.datumFakture = datumFakture;
        this.datumValute = datumValute;
        this.osnovica = osnovica;
        this.ukupanPDV = ukupanPDV;
        this.iznosZaPlacanje = iznosZaPlacanje;
        this.status = status;
        this.preduzece = preduzece;
        this.poslovniPartner = poslovniPartner;
        this.poslovnaGodina = poslovnaGodina;
        this.stavkeFakture = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBrojFakture() {
        return brojFakture;
    }

    public void setBrojFakture(int brojFakture) {
        this.brojFakture = brojFakture;
    }

    public Date getDatumFakture() {
        return datumFakture;
    }

    public void setDatumFakture(Date datumFakture) {
        this.datumFakture = datumFakture;
    }

    public Date getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(Date datumValute) {
        this.datumValute = datumValute;
    }

    public double getOsnovica() {
        return osnovica;
    }

    public void setOsnovica(double osnovica) {
        this.osnovica = osnovica;
    }

    public double getUkupanPDV() {
        return ukupanPDV;
    }

    public void setUkupanPDV(double ukupanPDV) {
        this.ukupanPDV = ukupanPDV;
    }

    public double getIznosZaPlacanje() {
        return iznosZaPlacanje;
    }

    public void setIznosZaPlacanje(double iznosZaPlacanje) {
        this.iznosZaPlacanje = iznosZaPlacanje;
    }

    public char[] getStatus() {
        return status;
    }

    public void setStatus(char[] status) {
        this.status = status;
    }

    public List<StavkaFakture> getStavkeFakture() {
        return stavkeFakture;
    }

    public void setStavkeFakture(List<StavkaFakture> stavkeFakture) {
        this.stavkeFakture = stavkeFakture;
    }

    public Preduzece getPreduzece() {
        return preduzece;
    }

    public void setPreduzece(Preduzece preduzece) {
        this.preduzece = preduzece;
    }

    public PoslovniPartner getPoslovniPartner() {
        return poslovniPartner;
    }

    public void setPoslovniPartner(PoslovniPartner poslovniPartner) {
        this.poslovniPartner = poslovniPartner;
    }

    public PoslovnaGodina getPoslovnaGodina() {
        return poslovnaGodina;
    }

    public void setPoslovnaGodina(PoslovnaGodina poslovnaGodina) {
        this.poslovnaGodina = poslovnaGodina;
    }
}
