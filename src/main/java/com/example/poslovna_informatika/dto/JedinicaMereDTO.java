package com.example.poslovna_informatika.dto;

import com.example.poslovna_informatika.model.GrupaRobe;
import com.example.poslovna_informatika.model.JedinicaMere;
import com.example.poslovna_informatika.model.Roba;
import com.example.poslovna_informatika.model.StavkaCenovnika;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class JedinicaMereDTO implements Serializable {


    private long id;
    private char[] naziv = new char[2];
    private List<RobaDTO> robeDTO;

    public JedinicaMereDTO() {
    }

    public JedinicaMereDTO(long id, char[] naziv, List<RobaDTO> robeDTO) {
        this.id = id;
        this.naziv = naziv;
        this.robeDTO = robeDTO;
    }

    public JedinicaMereDTO(JedinicaMere jedinicaMere) {
        this.id = jedinicaMere.getId();
        this.naziv = jedinicaMere.getNaziv();
        this.robeDTO = makeGrupaRobeDTO(jedinicaMere.getRoba());
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public char[] getNaziv() {
        return naziv;
    }

    public void setNaziv(char[] naziv) {
        this.naziv = naziv;
    }


    public List<RobaDTO> getRobeDTO() {
        return robeDTO;
    }

    public void setRobeDTO(List<RobaDTO> robeDTO) {
        this.robeDTO = robeDTO;
    }

    private List<RobaDTO> makeGrupaRobeDTO(List<Roba> roba) {

        return roba.stream().map(temp -> {
            RobaDTO dto = new RobaDTO(temp);
            return dto;
        }).collect(Collectors.toList());
    }
}
