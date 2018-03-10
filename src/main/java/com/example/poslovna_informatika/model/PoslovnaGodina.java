package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class PoslovnaGodina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private int godina;

    @Column
    private boolean zakljucena;

    @OneToMany
    private List<Faktura> fakture;

    public PoslovnaGodina() {
    }

    public PoslovnaGodina(int godina, boolean zakljucena, List<Faktura> fakture) {
        this.godina = godina;
        this.zakljucena = zakljucena;
        this.fakture = fakture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGodina() {
        return godina;
    }

    public void setGodina(int godina) {
        this.godina = godina;
    }

    public boolean isZakljucena() {
        return zakljucena;
    }

    public void setZakljucena(boolean zakljucena) {
        this.zakljucena = zakljucena;
    }

    public List<Faktura> getFakture() {
        return fakture;
    }

    public void setFakture(List<Faktura> fakture) {
        this.fakture = fakture;
    }
}
