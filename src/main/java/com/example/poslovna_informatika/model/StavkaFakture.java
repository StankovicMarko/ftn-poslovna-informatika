package com.example.poslovna_informatika.model;

import javax.persistence.*;

@Entity
public class StavkaFakture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private int kolicina;

    @Column(nullable = false)
    private double jedinicnaCena;

    @Column(nullable = false)
    private double rabat;

    @Column(nullable = false)
    private double osnovicaZaPDV;

    @Column(nullable = false)
    private double procenatPDV;

    @Column(nullable = false)
    private double iznosPDV;

    @Column(nullable = false)
    private double iznosStavke;

    @ManyToOne
    @JoinColumn(name = "roba")
    private Roba roba;

    @ManyToOne
    @JoinColumn(name = "faktura")
    private Faktura faktura;

    public StavkaFakture() {
    }

    public StavkaFakture(int kolicina, double jedinicnaCena, double rabat, double osnovicaZaPDV, double procenatPDV, double iznosPDV, double iznosStavke, Roba roba, Faktura faktura) {
        this.kolicina = kolicina;
        this.jedinicnaCena = jedinicnaCena;
        this.rabat = rabat;
        this.osnovicaZaPDV = osnovicaZaPDV;
        this.procenatPDV = procenatPDV;
        this.iznosPDV = iznosPDV;
        this.iznosStavke = iznosStavke;
        this.roba = roba;
        this.faktura = faktura;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getJedinicnaCena() {
        return jedinicnaCena;
    }

    public void setJedinicnaCena(double jedinicnaCena) {
        this.jedinicnaCena = jedinicnaCena;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    public double getOsnovicaZaPDV() {
        return osnovicaZaPDV;
    }

    public void setOsnovicaZaPDV(double osnovicaZaPDV) {
        this.osnovicaZaPDV = osnovicaZaPDV;
    }

    public double getProcenatPDV() {
        return procenatPDV;
    }

    public void setProcenatPDV(double procenatPDV) {
        this.procenatPDV = procenatPDV;
    }

    public double getIznosPDV() {
        return iznosPDV;
    }

    public void setIznosPDV(double iznosPDV) {
        this.iznosPDV = iznosPDV;
    }

    public double getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(double iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public Roba getRoba() {
        return roba;
    }

    public void setRoba(Roba roba) {
        this.roba = roba;
    }

    public Faktura getFaktura() {
        return faktura;
    }

    public void setFaktura(Faktura faktura) {
        this.faktura = faktura;
    }
}
