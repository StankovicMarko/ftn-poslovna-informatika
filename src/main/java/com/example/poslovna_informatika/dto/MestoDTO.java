package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class MestoDTO implements Serializable {


    private long id;
    private String grad;
    private String drzava;
    private List<PreduzeceDTO> preduzecaDTO;
    private List<PoslovniPartnerDTO> poslovniPartneriDTO;

    public MestoDTO() {
    }

    public MestoDTO(long id, String grad, String drzava, List<PreduzeceDTO> preduzecaDTO,
                    List<PoslovniPartnerDTO> poslovniPartneriDTO) {
        this.id = id;
        this.grad = grad;
        this.drzava = drzava;
        this.preduzecaDTO = preduzecaDTO;
        this.poslovniPartneriDTO = poslovniPartneriDTO;
    }

    public MestoDTO(Mesto mesto) {
        this.id = mesto.getId();
        this.grad = mesto.getGrad();
        this.drzava = mesto.getDrzava();
        this.preduzecaDTO = makePreduzecaDTO(mesto.getPreduzeca());
        this.poslovniPartneriDTO = makePoslovniPartneriDTO(mesto.getPoslovniPartneri());
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public List<PreduzeceDTO> getPreduzecaDTO() {
        return preduzecaDTO;
    }

    public void setPreduzecaDTO(List<PreduzeceDTO> preduzecaDTO) {
        this.preduzecaDTO = preduzecaDTO;
    }

    public List<PoslovniPartnerDTO> getPoslovniPartneriDTO() {
        return poslovniPartneriDTO;
    }

    public void setPoslovniPartneriDTO(List<PoslovniPartnerDTO> poslovniPartneriDTO) {
        this.poslovniPartneriDTO = poslovniPartneriDTO;
    }

    private List<PreduzeceDTO> makePreduzecaDTO(List<Preduzece> preduzeca) {

        return preduzeca.stream().map(temp -> {
            PreduzeceDTO dto = new PreduzeceDTO(temp);
            return dto;
        }).collect(Collectors.toList());
    }

    private List<PoslovniPartnerDTO> makePoslovniPartneriDTO(List<PoslovniPartner> poslovniPartneri) {

        return poslovniPartneri.stream().map(temp -> {
            PoslovniPartnerDTO dto = new PoslovniPartnerDTO(temp);
            return dto;
        }).collect(Collectors.toList());
    }

}
