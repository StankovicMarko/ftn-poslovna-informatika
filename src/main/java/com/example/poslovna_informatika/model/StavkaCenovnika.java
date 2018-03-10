package com.example.poslovna_informatika.model;

import javax.persistence.*;

@Entity
public class StavkaCenovnika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double cena;

    @ManyToOne
    @JoinColumn(name = "roba")
    private Roba roba;

    @ManyToOne
    @JoinColumn(name = "cenovnik")
    private Cenovnik cenovnik;


    public StavkaCenovnika() {
    }

    public StavkaCenovnika(double cena, Roba roba, Cenovnik cenovnik) {
        this.cena = cena;
        this.roba = roba;
        this.cenovnik = cenovnik;
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

    public Roba getRoba() {
        return roba;
    }

    public void setRoba(Roba roba) {
        this.roba = roba;
    }

    public Cenovnik getCenovnik() {
        return cenovnik;
    }

    public void setCenovnik(Cenovnik cenovnik) {
        this.cenovnik = cenovnik;
    }
}
