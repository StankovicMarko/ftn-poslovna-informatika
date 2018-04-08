package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.PDV;
import com.example.poslovna_informatika.model.StopaPDV;

import java.util.List;
import java.util.stream.Collectors;

public class PdvDTO {

    private long id;
    private String naziv;
    private List<Long> grupaRobeIds;
    private List<StopaPdvDTO> stopePdvDTO;

    public PdvDTO() {
    }

    public PdvDTO(long id, String naziv, List<Long> grupaRobeIds, List<StopaPdvDTO> stopePdvDTO) {
        this.id = id;
        this.naziv = naziv;
        this.grupaRobeIds = grupaRobeIds;
        this.stopePdvDTO = stopePdvDTO;
    }

    public PdvDTO(PDV pdv) {
        this.id = pdv.getId();
        this.naziv = pdv.getNaziv();
        this.grupaRobeIds = makeGrupaRobeIds(pdv.getGrupaRobe());
        this.stopePdvDTO = makeStopePdvDTO(pdv.getStopePDV());
    }

    private List<StopaPdvDTO> makeStopePdvDTO(List<StopaPDV> stopaPDVS) {
        return stopaPDVS.stream().map(StopaPdvDTO::new).collect(Collectors.toList());
    }

    private List<Long> makeGrupaRobeIds(List<GrupaRobe> grupaRobes) {
        return grupaRobes.stream().map(GrupaRobe::getId).collect(Collectors.toList());
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

    public List<Long> getGrupaRobeIds() {
        return grupaRobeIds;
    }

    public void setGrupaRobeIds(List<Long> grupaRobeIds) {
        this.grupaRobeIds = grupaRobeIds;
    }

    public List<StopaPdvDTO> getStopePdvDTO() {
        return stopePdvDTO;
    }

    public void setStopePdvDTO(List<StopaPdvDTO> stopePdvDTO) {
        this.stopePdvDTO = stopePdvDTO;
    }
}
