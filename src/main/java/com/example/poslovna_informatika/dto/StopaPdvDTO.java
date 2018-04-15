package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.StopaPDV;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class StopaPdvDTO {

    private long id;
    private double procenat;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date datumVazenja;
    private long pdvId;

    public StopaPdvDTO() {
    }

    public StopaPdvDTO(long id, double procenat, Date datumVazenja, long pdvId) {
        this.id = id;
        this.procenat = procenat;
        this.datumVazenja = datumVazenja;
        this.pdvId = pdvId;
    }

    public StopaPdvDTO(StopaPDV stopaPDV) {
        this(stopaPDV.getId(),
                stopaPDV.getProcenat(),
                stopaPDV.getDatumVazenja(),
                stopaPDV.getPdv().getId());
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

    public long getPdvId() {
        return pdvId;
    }

    public void setPdvId(long pdvId) {
        this.pdvId = pdvId;
    }
}



