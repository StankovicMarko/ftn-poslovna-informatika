package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class JedinicaMere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length = 2)
    private char[] naziv = new char[2];

    @OneToMany
    private List<Roba> roba;

    public JedinicaMere() {
    }

    public JedinicaMere(char[] naziv, List<Roba> roba) {
        this.naziv = naziv;
        this.roba = roba;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char[] getNaziv() {
        return naziv;
    }

    public void setNaziv(char[] naziv) {
        this.naziv = naziv;
    }

    public List<Roba> getRoba() {
        return roba;
    }

    public void setRoba(List<Roba> roba) {
        this.roba = roba;
    }
}
