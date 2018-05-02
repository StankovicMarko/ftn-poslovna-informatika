package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Cenovnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private Date datumVazenja;

    @OneToMany
    private List<StavkaCenovnika> stavkeCenovnika;

    @ManyToOne
    @JoinColumn(name = "preduzece")
    private Preduzece preduzece;

    public Cenovnik() {
    }

    public Cenovnik(Date datumVazenja, Preduzece preduzece) {
        this.datumVazenja = datumVazenja;
        this.preduzece = preduzece;
        this.stavkeCenovnika = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(Date datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public List<StavkaCenovnika> getStavkeCenovnika() {
        return stavkeCenovnika;
    }

    public void setStavkeCenovnika(List<StavkaCenovnika> stavkeCenovnika) {
        this.stavkeCenovnika = stavkeCenovnika;
    }

    public Preduzece getPreduzece() {
        return preduzece;
    }

    public void setPreduzece(Preduzece preduzece) {
        this.preduzece = preduzece;
    }

    @Override
    public String toString() {
        return "Cenovnik{" +
                "id=" + id +
                ", datumVazenja=" + datumVazenja +
                ", stavkeCenovnika=" + stavkeCenovnika +
                ", preduzece=" + preduzece +
                '}';
    }


}
