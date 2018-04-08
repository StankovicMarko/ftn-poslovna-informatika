package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.StopaPDV;

import java.util.Date;

public class StopaPdvDTO {

    private long id;
    private double procenat;
    private Date datumVazenja;
    private PDV pdv;

    public StopaPdvDTO() {
    }

    public StopaPdvDTO(long id, double procenat, Date datumVazenja, PDV pdv) {
        this.id = id;
        this.procenat = procenat;
        this.datumVazenja = datumVazenja;
        this.pdv = pdv;
    }

    public StopaPdvDTO(StopaPDV stopaPDV) {
        this(stopaPDV.getId(), stopaPDV.getProcenat(), stopaPDV.getDatumVazenja(), stopaPDV.getPdv());
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



