package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaCenovnika;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

public class StavkaCenovnikaDTO implements Serializable {

    private long id;

    private double cena;

    private long robaId;

    private long cenovnikId;

    private String nazivRobe;


    public StavkaCenovnikaDTO() {
    }

    public StavkaCenovnikaDTO(long id, double cena, long robaId, long cenovnikId, String nazivRobe) {
        this.id = id;
        this.cena = cena;
        this.robaId = robaId;
        this.cenovnikId = cenovnikId;
        this.nazivRobe = nazivRobe;
    }


    public StavkaCenovnikaDTO(StavkaCenovnika stavkaCenovnika) {
        this.id = stavkaCenovnika.getId();
        this.cena = stavkaCenovnika.getCena();
        this.robaId = stavkaCenovnika.getRoba().getId();
        this.cenovnikId = stavkaCenovnika.getCenovnik().getId();
        this.nazivRobe = stavkaCenovnika.getRoba().getNaziv();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public long getRobaId() {
        return robaId;
    }

    public void setRobaId(long robaId) {
        this.robaId = robaId;
    }

    public long getCenovnikId() {
        return cenovnikId;
    }

    public void setCenovnikId(long cenovnikId) {
        this.cenovnikId = cenovnikId;
    }

    public String getNazivRobe() {
        return nazivRobe;
    }

    public void setNazivRobe(String nazivRobe) {
        this.nazivRobe = nazivRobe;
    }
}
