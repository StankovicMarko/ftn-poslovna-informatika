package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.Cenovnik;
import com.example.poslovna_informatika.model.StavkaCenovnika;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class CenovnikDTO implements Serializable {


    private long id;
    private Date datumVazenja;
    private List<StavkaCenovnikaDTO> stavkeCenovnikaDTO;
    private long preduzeceID;


    public CenovnikDTO() {
    }

    public CenovnikDTO(long id, Date datumVazenja, List<StavkaCenovnikaDTO> stavkeCenovnikaDTO, long preduzeceID) {
        this.id = id;
        this.datumVazenja = datumVazenja;
        this.stavkeCenovnikaDTO = stavkeCenovnikaDTO;
        this.preduzeceID = preduzeceID;
    }

    public CenovnikDTO(Cenovnik cenovnik) {
        this.id = cenovnik.getId();
        this.datumVazenja = cenovnik.getDatumVazenja();
        this.stavkeCenovnikaDTO = makeStavkeCenovnikaDTO(cenovnik.getStavkeCenovnika());
        this.preduzeceID = cenovnik.getPreduzece().getId();
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

    public long getPreduzeceID() {
        return preduzeceID;
    }

    public void setPreduzeceID(long preduzeceID) {
        this.preduzeceID = preduzeceID;
    }

    private List <StavkaCenovnikaDTO> makeStavkeCenovnikaDTO (List<StavkaCenovnika> stavkeCenovnika){

        return stavkeCenovnika.stream().map(temp -> {
            StavkaCenovnikaDTO dto = new StavkaCenovnikaDTO(temp);
            return dto;
        }).collect(Collectors.toList());}
}
