package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Mesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 150)
    private String grad;

    @Column(nullable = false)
    private String drzava;

    @OneToMany
    private List<Preduzece> preduzeca;

    @OneToMany
    private List<PoslovniPartner> poslovniPartneri;

    public Mesto() {
    }

    public Mesto(String grad, String drzava) {
        this.grad = grad;
        this.drzava = drzava;
        this.preduzeca = new ArrayList<>();
        this.poslovniPartneri = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public List<Preduzece> getPreduzeca() {
        return preduzeca;
    }

    public void setPreduzeca(List<Preduzece> preduzeca) {
        this.preduzeca = preduzeca;
    }

    public List<PoslovniPartner> getPoslovniPartneri() {
        return poslovniPartneri;
    }

    public void setPoslovniPartneri(List<PoslovniPartner> poslovniPartneri) {
        this.poslovniPartneri = poslovniPartneri;
    }
}
