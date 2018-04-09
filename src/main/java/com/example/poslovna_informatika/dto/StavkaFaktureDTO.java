package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.StavkaFakture;

public class StavkaFaktureDTO {

    private long id;
    private int kolicina;
    private double jedinicnaCena;
    private double rabat;
    private double osnovicaZaPDV;
    private double procenatPDV;
    private double iznosPDV;
    private double iznosStavke;
    private long robaId;
    private long fakturaId;
    private String nazivRobe;

    public StavkaFaktureDTO() {
    }

    public StavkaFaktureDTO(long id, int kolicina, double jedinicnaCena, double rabat, double osnovicaZaPDV,
                            double procenatPDV, double iznosPDV, double iznosStavke, long robaId, long fakturaId,
                            String nazivRobe) {
        this.id = id;
        this.kolicina = kolicina;
        this.jedinicnaCena = jedinicnaCena;
        this.rabat = rabat;
        this.osnovicaZaPDV = osnovicaZaPDV;
        this.procenatPDV = procenatPDV;
        this.iznosPDV = iznosPDV;
        this.iznosStavke = iznosStavke;
        this.robaId = robaId;
        this.fakturaId = fakturaId;
        this.nazivRobe = nazivRobe;
    }

    public StavkaFaktureDTO(StavkaFakture stavkaFakture) {
        this.id = stavkaFakture.getId();
        this.kolicina = stavkaFakture.getKolicina();
        this.jedinicnaCena = stavkaFakture.getJedinicnaCena();
        this.rabat = stavkaFakture.getRabat();
        this.osnovicaZaPDV = stavkaFakture.getOsnovicaZaPDV();
        this.procenatPDV = stavkaFakture.getProcenatPDV();
        this.iznosPDV = stavkaFakture.getIznosPDV();
        this.iznosStavke = stavkaFakture.getIznosStavke();
        this.robaId = stavkaFakture.getRoba().getId();
        this.fakturaId = stavkaFakture.getFaktura().getId();
        this.nazivRobe = stavkaFakture.getRoba().getNaziv();
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

    public long getRobaId() {
        return robaId;
    }

    public void setRobaId(long robaId) {
        this.robaId = robaId;
    }

    public long getFakturaId() {
        return fakturaId;
    }

    public void setFakturaId(long fakturaId) {
        this.fakturaId = fakturaId;
    }

    public String getNazivRobe() {
        return nazivRobe;
    }

    public void setNazivRobe(String nazivRobe) {
        this.nazivRobe = nazivRobe;
    }
}
