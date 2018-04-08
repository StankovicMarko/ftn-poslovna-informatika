package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FakturaDTO implements Serializable {


    private long id;
    private int brojFakture;
    private Date datumFakture;
    private Date datumValute;
    private double osnovica;
    private double ukupanPDV;
    private double iznosZaPlacanje;
    private char[] status = new char[2];
    private List<StavkaFaktureDTO> stavkeFaktureDTO;
    private long preduzeceId;
    private long poslovniPartnerId;
    private long poslovnaGodinaId;

    public FakturaDTO() {
    }

    public FakturaDTO(long id, int brojFakture, Date datumFakture, Date datumValute, double osnovica, double ukupanPDV,
                      double iznosZaPlacanje, char[] status, List<StavkaFaktureDTO> stavkeFaktureDTO, long preduzeceId,
                      long poslovniPartnerId, long poslovnaGodinaId) {
        this.id = id;
        this.brojFakture = brojFakture;
        this.datumFakture = datumFakture;
        this.datumValute = datumValute;
        this.osnovica = osnovica;
        this.ukupanPDV = ukupanPDV;
        this.iznosZaPlacanje = iznosZaPlacanje;
        this.status = status;
        this.stavkeFaktureDTO = stavkeFaktureDTO;
        this.preduzeceId = preduzeceId;
        this.poslovniPartnerId = poslovniPartnerId;
        this.poslovnaGodinaId = poslovnaGodinaId;
    }


    public FakturaDTO(Faktura faktura) {
        this.id = faktura.getId();
        this.brojFakture = faktura.getBrojFakture();
        this.datumFakture = faktura.getDatumFakture();
        this.datumValute = faktura.getDatumValute();
        this.osnovica = faktura.getOsnovica();
        this.ukupanPDV = faktura.getUkupanPDV();
        this.iznosZaPlacanje = faktura.getIznosZaPlacanje();
        this.status = faktura.getStatus();
        this.stavkeFaktureDTO = makeStavkeFaktureDTO(faktura.getStavkeFakture());
        this.preduzeceId = faktura.getPreduzece().getId();
        this.poslovniPartnerId = faktura.getPoslovniPartner().getId();
        this.poslovnaGodinaId = faktura.getPoslovnaGodina().getId();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBrojFakture() {
        return brojFakture;
    }

    public void setBrojFakture(int brojFakture) {
        this.brojFakture = brojFakture;
    }

    public Date getDatumFakture() {
        return datumFakture;
    }

    public void setDatumFakture(Date datumFakture) {
        this.datumFakture = datumFakture;
    }

    public Date getDatumValute() {
        return datumValute;
    }

    public void setDatumValute(Date datumValute) {
        this.datumValute = datumValute;
    }

    public double getOsnovica() {
        return osnovica;
    }

    public void setOsnovica(double osnovica) {
        this.osnovica = osnovica;
    }

    public double getUkupanPDV() {
        return ukupanPDV;
    }

    public void setUkupanPDV(double ukupanPDV) {
        this.ukupanPDV = ukupanPDV;
    }

    public double getIznosZaPlacanje() {
        return iznosZaPlacanje;
    }

    public void setIznosZaPlacanje(double iznosZaPlacanje) {
        this.iznosZaPlacanje = iznosZaPlacanje;
    }

    public char[] getStatus() {
        return status;
    }

    public void setStatus(char[] status) {
        this.status = status;
    }

    public List<StavkaFaktureDTO> getStavkeFaktureDTO() {
        return stavkeFaktureDTO;
    }

    public void setStavkeFaktureDTO(List<StavkaFaktureDTO> stavkeFaktureDTO) {
        this.stavkeFaktureDTO = stavkeFaktureDTO;
    }

    public long getPreduzeceId() {
        return preduzeceId;
    }

    public void setPreduzeceId(long preduzeceId) {
        this.preduzeceId = preduzeceId;
    }

    public long getPoslovniPartnerId() {
        return poslovniPartnerId;
    }

    public void setPoslovniPartnerId(long poslovniPartnerId) {
        this.poslovniPartnerId = poslovniPartnerId;
    }

    public long getPoslovnaGodinaId() {
        return poslovnaGodinaId;
    }

    public void setPoslovnaGodinaId(long poslovnaGodinaId) {
        this.poslovnaGodinaId = poslovnaGodinaId;
    }

    private List <StavkaFaktureDTO> makeStavkeFaktureDTO (List<StavkaFakture> stavkaFaktures){

        return stavkaFaktures.stream().map(temp -> {
            StavkaFaktureDTO dto = new StavkaFaktureDTO(temp);
            return dto;
        }).collect(Collectors.toList());}



}
