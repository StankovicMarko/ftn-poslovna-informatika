package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.StavkaCenovnika;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CenovnikDTO {

    private long id;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date datumVazenja;
    private List<StavkaCenovnikaDTO> stavkeCenovnikaDTO;
    private long preduzeceId;

    public CenovnikDTO() {
    }


    public CenovnikDTO(Date datumVazenja, long preduzeceId) {
        this.datumVazenja = datumVazenja;
        this.preduzeceId = preduzeceId;
    }

    public CenovnikDTO(long id, Date datumVazenja, List<StavkaCenovnikaDTO> stavkeCenovnikaDTO, long preduzeceId) {
        this.id = id;
        this.datumVazenja = datumVazenja;
        this.stavkeCenovnikaDTO = stavkeCenovnikaDTO;
        this.preduzeceId = preduzeceId;
    }

    public CenovnikDTO(Cenovnik cenovnik) {
        this.id = cenovnik.getId();
        this.datumVazenja = cenovnik.getDatumVazenja();
        this.stavkeCenovnikaDTO = makeStavkeCenovnikaDTO(cenovnik.getStavkeCenovnika());
        this.preduzeceId = cenovnik.getPreduzece().getId();
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

    public List<StavkaCenovnikaDTO> getStavkeCenovnikaDTO() {
        return stavkeCenovnikaDTO;
    }

    public void setStavkeCenovnikaDTO(List<StavkaCenovnikaDTO> stavkeCenovnikaDTO) {
        this.stavkeCenovnikaDTO = stavkeCenovnikaDTO;
    }

    public long getPreduzeceId() {
        return preduzeceId;
    }

    public void setPreduzeceId(long preduzeceId) {
        this.preduzeceId = preduzeceId;
    }

    private List<StavkaCenovnikaDTO> makeStavkeCenovnikaDTO(List<StavkaCenovnika> stavkeCenovnika) {
        return stavkeCenovnika.stream().map(StavkaCenovnikaDTO::new).collect(Collectors.toList());
    }
}
