package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.Roba;

import java.util.List;
import java.util.stream.Collectors;

public class GrupaRobeDTO {

    private long id;
    private String naziv;
    private List<RobaDTO> robeDTO;
    private long preduzeceId;
    private long pdvId;

    public GrupaRobeDTO() {
    }

    public GrupaRobeDTO(long id, String naziv, List<RobaDTO> robeDTO, long preduzeceId, long pdvId) {
        this.id = id;
        this.naziv = naziv;
        this.robeDTO = robeDTO;
        this.preduzeceId = preduzeceId;
        this.pdvId = pdvId;
    }

    public GrupaRobeDTO(GrupaRobe grupaRobe) {
        this.id = grupaRobe.getId();
        this.naziv = grupaRobe.getNaziv();
        this.robeDTO = makeGrupaRobeDTO(grupaRobe.getRobe());
        this.preduzeceId = grupaRobe.getPreduzece().getId();
        this.pdvId = grupaRobe.getPdv().getId();
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

    public List<RobaDTO> getRobeDTO() {
        return robeDTO;
    }

    public void setRobeDTO(List<RobaDTO> robeDTO) {
        this.robeDTO = robeDTO;
    }

    public long getPreduzeceId() {
        return preduzeceId;
    }

    public void setPreduzeceId(long preduzeceId) {
        this.preduzeceId = preduzeceId;
    }

    public long getPdvId() {
        return pdvId;
    }

    public void setPdvId(long pdvId) {
        this.pdvId = pdvId;
    }

    private List<RobaDTO> makeGrupaRobeDTO(List<Roba> roba) {
        return roba.stream().map(RobaDTO::new).collect(Collectors.toList());
    }
}
