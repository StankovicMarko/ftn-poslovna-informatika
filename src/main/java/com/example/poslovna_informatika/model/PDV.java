package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PDV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 30)
    private String naziv;

    @OneToMany
    private List<GrupaRobe> grupaRobe;

    @OneToMany
    private List<StopaPDV> stopePDV;

    public PDV() {
    }

    public PDV(String naziv, List<GrupaRobe> grupaRobe, List<StopaPDV> stopePDV) {
        this.naziv = naziv;
        this.grupaRobe = grupaRobe;
        this.stopePDV = stopePDV;
    }

    public PDV(String naziv) {
        this.naziv = naziv;
        this.grupaRobe = new ArrayList<>();
        this.stopePDV = new ArrayList<>();
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

    public List<GrupaRobe> getGrupaRobe() {
        return grupaRobe;
    }

    public void setGrupaRobe(List<GrupaRobe> grupaRobe) {
        this.grupaRobe = grupaRobe;
    }

    public List<StopaPDV> getStopePDV() {
        return stopePDV;
    }

    public void setStopePDV(List<StopaPDV> stopePDV) {
        this.stopePDV = stopePDV;
    }
}
