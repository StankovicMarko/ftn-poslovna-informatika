package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class GrupaRobe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 100)
    private String naziv;

    @OneToMany
    private List<Roba> robe;

    @ManyToOne
    @JoinColumn(name = "preduzece")
    private Preduzece preduzece;

    @ManyToOne
    @JoinColumn(name = "pdv")
    private PDV pdv;

    public GrupaRobe() {
    }

    public GrupaRobe(String naziv, List<Roba> robe, Preduzece preduzece, PDV pdv) {
        this.naziv = naziv;
        this.robe = robe;
        this.preduzece = preduzece;
        this.pdv = pdv;
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

    public List<Roba> getRobe() {
        return robe;
    }

    public void setRobe(List<Roba> robe) {
        this.robe = robe;
    }

    public Preduzece getPreduzece() {
        return preduzece;
    }

    public void setPreduzece(Preduzece preduzece) {
        this.preduzece = preduzece;
    }

    public PDV getPdv() {
        return pdv;
    }

    public void setPdv(PDV pdv) {
        this.pdv = pdv;
    }
}
