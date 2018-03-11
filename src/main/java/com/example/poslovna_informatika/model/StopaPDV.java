package com.example.poslovna_informatika.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class StopaPDV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private double procenat;

    @Column(nullable = false)
    private Date datumVazenja;

    @ManyToOne
    @JoinColumn(name = "pdv")
    private PDV pdv;

    public StopaPDV() {
    }

    public StopaPDV(double procenat, Date datumVazenja, PDV pdv) {
        this.procenat = procenat;
        this.datumVazenja = datumVazenja;
        this.pdv = pdv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getProcenat() {
        return procenat;
    }

    public void setProcenat(double procenat) {
        this.procenat = procenat;
    }

    public Date getDatumVazenja() {
        return datumVazenja;
    }

    public void setDatumVazenja(Date datumVazenja) {
        this.datumVazenja = datumVazenja;
    }

    public PDV getPdv() {
        return pdv;
    }

    public void setPdv(PDV pdv) {
        this.pdv = pdv;
    }
}
