package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.StavkaFakture;

public class StavkaFaktureDTO {

    private long id;
    private int kolicina; //unosim
    private double jedinicnaCena; //dobijem iz
    private double rabat; //unosim    unos-100
    private double osnovicaZaPDV; // kol * cena * (unos - 100) * 100
    private double procenatPDV; // ucitaj stopu pdv
    private double iznosPDV; // osnovica * procenat / 100
    private double iznosStavke; // osnovica + iznos pdv
    private long robaId;
    private long fakturaId;
    private String nazivRobe;
    private long stavkaCenovnikaId;


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

    public StavkaFaktureDTO(int kolicina, double rabat, int stavkaCenovnikaId) {
        this.kolicina = kolicina;
        this.rabat= rabat;
        this.stavkaCenovnikaId=stavkaCenovnikaId;
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

    public long getStavkaCenovnikaId() {
        return stavkaCenovnikaId;
    }

    public void setStavkaCenovnikaId(long stavkaCenovnikaId) {
        this.stavkaCenovnikaId = stavkaCenovnikaId;
    }

    @Override
    public String toString() {
        return "StavkaFaktureDTO{" +
                "id=" + id +
                ", kolicina=" + kolicina +
                ", jedinicnaCena=" + jedinicnaCena +
                ", rabat=" + rabat +
                ", osnovicaZaPDV=" + osnovicaZaPDV +
                ", procenatPDV=" + procenatPDV +
                ", iznosPDV=" + iznosPDV +
                ", iznosStavke=" + iznosStavke +
                ", robaId=" + robaId +
                ", fakturaId=" + fakturaId +
                ", nazivRobe='" + nazivRobe + '\'' +
                '}';
    }
}
