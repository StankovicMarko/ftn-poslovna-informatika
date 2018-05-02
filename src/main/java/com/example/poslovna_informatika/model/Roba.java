package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Roba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String naziv;

    @OneToMany
    private List<StavkaCenovnika> stavkeCenovnika;

    @OneToMany
    private List<StavkaFakture> stavkaFakture;

    @ManyToOne
    @JoinColumn(name = "jedinica_mere")
    private JedinicaMere jedinicaMere;

    @ManyToOne
    @JoinColumn(name = "grupa_robe")
    private GrupaRobe grupaRobe;

    public Roba() {
    }

    public Roba(String naziv, JedinicaMere jedinicaMere, GrupaRobe grupaRobe) {
        this.naziv = naziv;
        this.jedinicaMere = jedinicaMere;
        this.grupaRobe = grupaRobe;
        this.stavkeCenovnika = new ArrayList<>();
        this.stavkaFakture = new ArrayList<>();
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

    public List<StavkaCenovnika> getStavkeCenovnika() {
        return stavkeCenovnika;
    }

    public void setStavkeCenovnika(List<StavkaCenovnika> stavkeCenovnika) {
        this.stavkeCenovnika = stavkeCenovnika;
    }

    public List<StavkaFakture> getStavkaFakture() {
        return stavkaFakture;
    }

    public void setStavkaFakture(List<StavkaFakture> stavkaFakture) {
        this.stavkaFakture = stavkaFakture;
    }

    public JedinicaMere getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(JedinicaMere jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    public GrupaRobe getGrupaRobe() {
        return grupaRobe;
    }

    public void setGrupaRobe(GrupaRobe grupaRobe) {
        this.grupaRobe = grupaRobe;
    }

    @Override
    public String toString() {
        return "Roba{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", stavkeCenovnika=" + stavkeCenovnika +
                ", stavkaFakture=" + stavkaFakture +
                ", jedinicaMere=" + jedinicaMere +
                ", grupaRobe=" + grupaRobe +
                '}';
    }
}
