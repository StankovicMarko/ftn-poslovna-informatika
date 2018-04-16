package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaCenovnika;
import com.example.poslovna_informatika.model.StavkaFakture;

import java.util.List;
import java.util.stream.Collectors;

public class RobaDTO {

    private long id;
    private String naziv;
    private List<Long> stavkeCenovnikaIds;
    private List<Long> stavkaFaktureIds;
    private long jedinicaMereId;
    private long grupaRobeId;

    public RobaDTO() {
    }

    public RobaDTO(long id, String naziv, List<Long> stavkeCenovnikaIds, List<Long> stavkaFaktureIds,
                   long jedinicMereId, long grupaRobeId) {
        this.id = id;
        this.naziv = naziv;
        this.stavkeCenovnikaIds = stavkeCenovnikaIds;
        this.stavkaFaktureIds = stavkaFaktureIds;
        this.jedinicaMereId = jedinicaMereId;
        this.grupaRobeId = grupaRobeId;
    }

    public RobaDTO(Roba roba) {
        this.id = roba.getId();
        this.naziv = roba.getNaziv();
        this.stavkeCenovnikaIds = makeStavkeCenovnikaIds(roba.getStavkeCenovnika());
        this.stavkaFaktureIds = makeStavkeFaktureIds(roba.getStavkaFakture());
        this.jedinicaMereId = roba.getJedinicaMere().getId();
        this.grupaRobeId = roba.getJedinicaMere().getId();
    }

    private List<Long> makeStavkeCenovnikaIds(List<StavkaCenovnika> stavkaCenovnika) {
        return stavkaCenovnika.stream().map(StavkaCenovnika::getId).collect(Collectors.toList());
    }

    private List<Long> makeStavkeFaktureIds(List<StavkaFakture> stavkaFakture) {
        return stavkaFakture.stream().map(StavkaFakture::getId).collect(Collectors.toList());
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

    public List<Long> getStavkeCenovnikaIds() {
        return stavkeCenovnikaIds;
    }

    public void setStavkeCenovnikaIds(List<Long> stavkeCenovnikaIds) {
        this.stavkeCenovnikaIds = stavkeCenovnikaIds;
    }

    public List<Long> getStavkaFaktureIds() {
        return stavkaFaktureIds;
    }

    public void setStavkaFaktureIds(List<Long> stavkaFaktureIds) {
        this.stavkaFaktureIds = stavkaFaktureIds;
    }

    public long getJedinicaMereId() {
        return jedinicaMereId;
    }

    public void setJedinicaMereId(long jedinicaMereId) {
        this.jedinicaMereId = jedinicaMereId;
    }

    public long getGrupaRobeId() {
        return grupaRobeId;
    }

    public void setGrupaRobeId(long grupaRobeId) {
        this.grupaRobeId = grupaRobeId;
    }
}

