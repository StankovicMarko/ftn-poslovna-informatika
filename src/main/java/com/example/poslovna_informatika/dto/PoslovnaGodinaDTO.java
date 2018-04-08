package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.Faktura;
import com.example.poslovna_informatika.model.PoslovnaGodina;

import java.util.List;
import java.util.stream.Collectors;

public class PoslovnaGodinaDTO {

    private long id;
    private int godina;
    private boolean zakljucena;
    private List<Long> faktureIds;

    public PoslovnaGodinaDTO() {
    }

    public PoslovnaGodinaDTO(long id, int godina, boolean zakljucena, List<Long> faktureIds) {
        this.id = id;
        this.godina = godina;
        this.zakljucena = zakljucena;
        this.faktureIds = faktureIds;
    }

    public PoslovnaGodinaDTO(PoslovnaGodina poslovnaGodina) {
        this.id = poslovnaGodina.getId();
        this.godina = poslovnaGodina.getGodina();
        this.zakljucena = poslovnaGodina.isZakljucena();
        this.faktureIds = makeFaktureIds(poslovnaGodina.getFakture());
    }

    private List<Long> makeFaktureIds(List<Faktura> fakturas) {
        return fakturas.stream().map(Faktura::getId).collect(Collectors.toList());
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

    public List<Long> getFaktureIds() {
        return faktureIds;
    }

    public void setFaktureIds(List<Long> faktureIds) {
        this.faktureIds = faktureIds;
    }
}

